package com.realcoding.chapter02.api.flight.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.config.TestBeanConfig;
import com.realcoding.chapter02.api.flight.presentation.converter.FlightControllerConverter;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightDeleteRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.flight.presentation.validator.FlightValidator;
import com.realcoding.chapter02.api.flight.service.logic.FlightService;
import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
@Import(TestBeanConfig.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightControllerConverter flightControllerConverter;

    @MockBean
    private FlightValidator flightValidator;

    @Test
    void getListAllFlight() throws Exception {
        // given
        List<FlightSO> mockFlightSOList = new ArrayList<>();
        BDDMockito.given(flightService.getListAllFlight()).willReturn(mockFlightSOList);

        List<FlightDTO> mockFlightDTOList = new ArrayList<>();
        BDDMockito.given(flightControllerConverter.toFlightDTOList(mockFlightSOList)).willReturn(mockFlightDTOList);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/list/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(mockFlightSOList.size())));
    }

    @Test
    void createFlight() throws Exception {
        // given
        FlightCreateRequest mockRequest = new FlightCreateRequest();
        FlightCreateRequestSO mockRequestSO = new FlightCreateRequestSO();
        List<FlightSO> mockFlightSOList = new ArrayList<>();
        List<FlightDTO> mockFlightDTOList = new ArrayList<>();

        BDDMockito.given(flightValidator.createRequestValidate(mockRequest)).willReturn(mockRequestSO);
        BDDMockito.given(flightService.createFlightList(mockRequestSO)).willReturn(mockFlightSOList);
        BDDMockito.given(flightControllerConverter.toFlightDTOList(mockFlightSOList)).willReturn(mockFlightDTOList);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(mockFlightDTOList.size())));
    }

    @Test
    void getFlightDetail() throws Exception {
        // given
        String mockFlightId = "someFlightId";
        FlightSO mockFlightSO = new FlightSO();
        // mockFlightSO 세부 설정...

        FlightDTO mockFlightDTO = new FlightDTO();
        // mockFlightDTO 세부 설정...

        BDDMockito.given(flightService.getFlightDetailByFlightId(mockFlightId)).willReturn(mockFlightSO);
        BDDMockito.given(flightControllerConverter.toFlightDTO(mockFlightSO)).willReturn(mockFlightDTO);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/detail/" + mockFlightId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.flightId", Matchers.is(mockFlightDTO.getFlightId())));
    }

    @Test
    void deleteFlights() throws Exception {
        // given
        FlightDeleteRequest mockRequest = new FlightDeleteRequest();
        // mockRequest 세부 설정...
        List<String> mockFlightIds = Arrays.asList("flightId1", "flightId2");
        BDDMockito.given(flightValidator.deleteRequestValidate(mockRequest)).willReturn(mockFlightIds);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete(ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1 + "/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockRequest)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}