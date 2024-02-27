package com.realcoding.chapter02.api.flight.presentation.validator;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.config.FlightConfig;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequestDetail;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightDeleteRequest;
import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightValidatorTest {

    @Mock
    private FlightDao flightDao;
    @Mock
    private FlightConfig flightConfig;
    @InjectMocks
    private FlightValidator flightValidator;

    @Test
    void createRequestValidate() {
        // given
        FlightCreateRequest flightCreateRequest = new FlightCreateRequest();
        List<FlightCreateRequestDetail> flightDetailList = new ArrayList<>();
        flightDetailList.add(FlightCreateRequestDetail.builder().flightName("AB123").sourceName("Korea").targetName("Japan").type(ApplicationConstants.FLIGHT_TYPE_ECONOMY).build());
        flightDetailList.add(FlightCreateRequestDetail.builder().flightName("US123").sourceName("Korea").targetName("US").type(ApplicationConstants.FLIGHT_TYPE_BUSINESS).build());
        flightCreateRequest.setFlightDetailList(flightDetailList);

        BDDMockito.given(flightConfig.getTypes()).willReturn(List.of(ApplicationConstants.FLIGHT_TYPE_BUSINESS, ApplicationConstants.FLIGHT_TYPE_ECONOMY));

        // when
        FlightCreateRequestSO requestSO = flightValidator.createRequestValidate(flightCreateRequest);

        // then
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertNotNull(requestSO),
                () -> Assertions.assertNotNull(requestSO.getFlightSOList()),
                () -> Assertions.assertFalse(CollectionUtils.isEmpty(requestSO.getFlightSOList())),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(0).getFlightName(), "AB123"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(0).getSourceName(), "Korea"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(0).getTargetName(), "Japan"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(0).getType(), ApplicationConstants.FLIGHT_TYPE_ECONOMY),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(1).getFlightName(), "US123"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(1).getSourceName(), "Korea"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(1).getTargetName(), "US"),
                () -> Assertions.assertEquals(requestSO.getFlightSOList().get(1).getType(), ApplicationConstants.FLIGHT_TYPE_BUSINESS)
        );
    }


    @Test
    void deleteRequestValidate_ValidInput() {
        // given
        FlightDeleteRequest flightDeleteRequest = new FlightDeleteRequest();
        flightDeleteRequest.setFlightIds(Collections.singletonList("flightId1"));

        // when & then
        List<String> flightIds = flightValidator.deleteRequestValidate(flightDeleteRequest);
        assertNotNull(flightIds);
        assertEquals(1, flightIds.size());
        assertEquals("flightId1", flightIds.get(0));
    }

    // Similarly, you can write tests for other scenarios of deleteRequestValidate method
}
