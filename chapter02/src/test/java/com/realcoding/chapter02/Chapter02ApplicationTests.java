package com.realcoding.chapter02;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequestDetail;
import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequestDetail;
import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequest;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequestDetail;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelDeleteRequest;
import com.realcoding.chapter02.api.travel.presentation.dto.out.TravelDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class Chapter02ApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Order(1)
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@DisplayName("Given 이코노미 항공편에서")
	public class EconomyFlightTest {

		private FlightDTO economyFlight;

		@BeforeAll
		void createEconomyFlight() throws Exception {
			FlightCreateRequest flightCreateRequest = new FlightCreateRequest();
			List<FlightCreateRequestDetail> flightDetailList = new ArrayList<>();
			flightDetailList.add(FlightCreateRequestDetail.builder()
					.flightName("KR2024")
					.sourceName("서울")
					.targetName("부산")
					.type("ECONOMY")
					.build());
			flightCreateRequest.setFlightDetailList(flightDetailList);

			ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/create")
							.content(new ObjectMapper().writeValueAsString(flightCreateRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().is(201));

			String responseString = resultActions.andReturn().getResponse().getContentAsString();
			List<FlightDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<FlightDTO>>() {
			});
			economyFlight = ret.get(0);
		}

		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
		@DisplayName("When 일반 승객은")
		public class regularPassengerTest {

			private PassengerDTO regularPassenger;
			private TravelDTO economyRegularTravel;

			@BeforeAll
			void createRegularPassenger() throws Exception {
				PassengerCreateRequest passengerCreateRequest = new PassengerCreateRequest();
				List<PassengerCreateRequestDetail> passengerDetailList = new ArrayList<>();
				passengerDetailList.add(PassengerCreateRequestDetail.builder()
						.passengerRRN("910505-1234567")
						.name("홍길동")
						.type(ApplicationConstants.PASSENGER_TYPE_REGULAR)
						.build());
				passengerCreateRequest.setPassengerDetailList(passengerDetailList);

				ResultActions resultActions = mockMvc.perform(
								MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_PASSENGER_CONSOLE_V1 + "/create")
										.content(new ObjectMapper().writeValueAsString(passengerCreateRequest))
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().is(201));
				String responseString = resultActions.andReturn().getResponse().getContentAsString();
				List<PassengerDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<PassengerDTO>>() {
				});
				regularPassenger = ret.get(0);
			}

			@Test
			@Order(1)
			@DisplayName("Then 이코노미 항공편에서 일반 승객은 여행을 만들 수 있다")
			void testCreateEconomyRegularTravel() throws Exception {
				TravelCreateRequest travelCreateRequest = new TravelCreateRequest();
				List<TravelCreateRequestDetail> travelDetailList = new ArrayList<>();
				travelDetailList.add(
						TravelCreateRequestDetail.builder()
								.passengerId(regularPassenger.getPassengerId())
								.flightId(economyFlight.getFlightId())
								.build()
				);
				travelCreateRequest.setTravelDetailList(travelDetailList);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/create")
								.content(new ObjectMapper().writeValueAsString(travelCreateRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(201));

				List<TravelDTO> ret = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<List<TravelDTO>>() {
				});
				economyRegularTravel = ret.get(0);
				Assertions.assertNotNull(economyRegularTravel.getTravelId());
			}

			@Test
			@Order(2)
			@DisplayName("Then 이코노미 항공편에서 일반 승객이 만든 여행은 삭제가 가능하다")
			void testDeleteEconomyRegularTravel() throws Exception {
				TravelDeleteRequest travelDeleteRequest = new TravelDeleteRequest();
				List<String> travelIds = new ArrayList<>();
				travelIds.add(economyRegularTravel.getTravelId());
				travelDeleteRequest.setTravelIds(travelIds);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.delete(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/delete")
								.content(new ObjectMapper().writeValueAsString(travelDeleteRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(204));

			}

		}


		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
		@DisplayName("When VIP 승객은")
		public class VipPassengerTest {

			private PassengerDTO vipPassenger;

			private TravelDTO economyVipTravel;

			@BeforeAll
			void createVipPassenger() throws Exception {
				PassengerCreateRequest passengerCreateRequest = new PassengerCreateRequest();
				List<PassengerCreateRequestDetail> passengerDetailList = new ArrayList<>();
				passengerDetailList.add(PassengerCreateRequestDetail.builder()
						.passengerRRN("910505-2222222")
						.name("성춘향")
						.type(ApplicationConstants.PASSEGNER_TYPE_VIP)
						.build());
				passengerCreateRequest.setPassengerDetailList(passengerDetailList);

				ResultActions resultActions = mockMvc.perform(
								MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_PASSENGER_CONSOLE_V1 + "/create")
										.content(new ObjectMapper().writeValueAsString(passengerCreateRequest))
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().is(201));
				String responseString = resultActions.andReturn().getResponse().getContentAsString();
				List<PassengerDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<PassengerDTO>>() {
				});
				vipPassenger = ret.get(0);
			}

			@Test
			@Order(1)
			@DisplayName("Then 이코노미 항공편에서 VIP 승객은 여행을 만들 수 있다")
			void testCreateEconomyVIPTravel() throws Exception {
				TravelCreateRequest travelCreateRequest = new TravelCreateRequest();
				List<TravelCreateRequestDetail> travelDetailList = new ArrayList<>();
				travelDetailList.add(
						TravelCreateRequestDetail.builder()
								.passengerId(vipPassenger.getPassengerId())
								.flightId(economyFlight.getFlightId())
								.build()
				);
				travelCreateRequest.setTravelDetailList(travelDetailList);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/create")
								.content(new ObjectMapper().writeValueAsString(travelCreateRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(201));

				List<TravelDTO> ret = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<List<TravelDTO>>() {
				});
				economyVipTravel = ret.get(0);
				Assertions.assertNotNull(economyVipTravel.getTravelId());
			}

			@Test
			@Order(2)
			@DisplayName("Then 이코노미 항공편에서 VIP 승객이 만든 여행은 삭제가 불가능하다")
			void testDeleteEconomyVipTravel() throws Exception {
				TravelDeleteRequest travelDeleteRequest = new TravelDeleteRequest();
				List<String> travelIds = new ArrayList<>();
				travelIds.add(economyVipTravel.getTravelId());
				travelDeleteRequest.setTravelIds(travelIds);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.delete(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/delete")
								.content(new ObjectMapper().writeValueAsString(travelDeleteRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(400));

			}

		}

	}


	@Order(2)
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@DisplayName("Given 비즈니스 항공편에서")
	public class businessFlightTest {

		private FlightDTO businessFlight;

		@BeforeAll
		void createBusinessFlight() throws Exception {
			FlightCreateRequest flightCreateRequest = new FlightCreateRequest();
			List<FlightCreateRequestDetail> flightDetailList = new ArrayList<>();
			flightDetailList.add(FlightCreateRequestDetail.builder()
					.flightName("US9999")
					.sourceName("서울")
					.targetName("캘리포니아")
					.type(ApplicationConstants.FLIGHT_TYPE_BUSINESS)
					.build());
			flightCreateRequest.setFlightDetailList(flightDetailList);

			ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/create")
							.content(new ObjectMapper().writeValueAsString(flightCreateRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(MockMvcResultMatchers.status().is(201));

			String responseString = resultActions.andReturn().getResponse().getContentAsString();
			List<FlightDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<FlightDTO>>() {
			});
			businessFlight = ret.get(0);
		}

		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
		@DisplayName("When 일반 승객은")
		public class regularPassengerTest {

			private PassengerDTO regularPassenger;
			private TravelDTO businessRegularTravel;

			@BeforeAll
			void createRegularPassenger() throws Exception {
				PassengerCreateRequest passengerCreateRequest = new PassengerCreateRequest();
				List<PassengerCreateRequestDetail> passengerDetailList = new ArrayList<>();
				passengerDetailList.add(PassengerCreateRequestDetail.builder()
						.passengerRRN("910505-1234567")
						.name("홍길동")
						.type(ApplicationConstants.PASSENGER_TYPE_REGULAR)
						.build());
				passengerCreateRequest.setPassengerDetailList(passengerDetailList);

				ResultActions resultActions = mockMvc.perform(
								MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_PASSENGER_CONSOLE_V1 + "/create")
										.content(new ObjectMapper().writeValueAsString(passengerCreateRequest))
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().is(201));
				String responseString = resultActions.andReturn().getResponse().getContentAsString();
				List<PassengerDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<PassengerDTO>>() {
				});
				regularPassenger = ret.get(0);
			}

			@Test
			@Order(1)
			@DisplayName("Then 비즈니스 항공편에서 일반 승객은 여행을 만들 수 없다")
			void testCreateEconomyRegularTravel() throws Exception {
				TravelCreateRequest travelCreateRequest = new TravelCreateRequest();
				List<TravelCreateRequestDetail> travelDetailList = new ArrayList<>();
				travelDetailList.add(
						TravelCreateRequestDetail.builder()
								.passengerId(regularPassenger.getPassengerId())
								.flightId(businessFlight.getFlightId())
								.build()
				);
				travelCreateRequest.setTravelDetailList(travelDetailList);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/create")
								.content(new ObjectMapper().writeValueAsString(travelCreateRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(400));

			}

		}


		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
		@DisplayName("When VIP 승객은")
		public class VipPassengerTest {

			private PassengerDTO vipPassenger;

			private TravelDTO businessVipTravel;

			@BeforeAll
			void createVipPassenger() throws Exception {
				PassengerCreateRequest passengerCreateRequest = new PassengerCreateRequest();
				List<PassengerCreateRequestDetail> passengerDetailList = new ArrayList<>();
				passengerDetailList.add(PassengerCreateRequestDetail.builder()
						.passengerRRN("910505-2222222")
						.name("성춘향")
						.type(ApplicationConstants.PASSEGNER_TYPE_VIP)
						.build());
				passengerCreateRequest.setPassengerDetailList(passengerDetailList);

				ResultActions resultActions = mockMvc.perform(
								MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_PASSENGER_CONSOLE_V1 + "/create")
										.content(new ObjectMapper().writeValueAsString(passengerCreateRequest))
										.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().is(201));
				String responseString = resultActions.andReturn().getResponse().getContentAsString();
				List<PassengerDTO> ret = new ObjectMapper().readValue(responseString, new TypeReference<List<PassengerDTO>>() {
				});
				vipPassenger = ret.get(0);
			}

			@Test
			@Order(1)
			@DisplayName("Then 비즈니스 항공편에서 VIP 승객은 여행을 만들 수 있다")
			void testCreateEconomyVIPTravel() throws Exception {
				TravelCreateRequest travelCreateRequest = new TravelCreateRequest();
				List<TravelCreateRequestDetail> travelDetailList = new ArrayList<>();
				travelDetailList.add(
						TravelCreateRequestDetail.builder()
								.passengerId(vipPassenger.getPassengerId())
								.flightId(businessFlight.getFlightId())
								.build()
				);
				travelCreateRequest.setTravelDetailList(travelDetailList);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/create")
								.content(new ObjectMapper().writeValueAsString(travelCreateRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(201));

				List<TravelDTO> ret = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<List<TravelDTO>>() {
				});
				businessVipTravel = ret.get(0);
				Assertions.assertNotNull(businessVipTravel.getTravelId());
			}

			@Test
			@Order(2)
			@DisplayName("Then 비즈니스 항공편에서 VIP 승객이 만든 여행은 삭제가 불가능하다")
			void testDeleteEconomyVipTravel() throws Exception {
				TravelDeleteRequest travelDeleteRequest = new TravelDeleteRequest();
				List<String> travelIds = new ArrayList<>();
				travelIds.add(businessVipTravel.getTravelId());
				travelDeleteRequest.setTravelIds(travelIds);

				ResultActions resultActions = mockMvc.perform(
						MockMvcRequestBuilders.delete(ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1 + "/delete")
								.content(new ObjectMapper().writeValueAsString(travelDeleteRequest))
								.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(MockMvcResultMatchers.status().is(400));

			}

		}

	}


}
