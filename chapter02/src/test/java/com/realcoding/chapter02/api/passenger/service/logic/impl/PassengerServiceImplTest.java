package com.realcoding.chapter02.api.passenger.service.logic.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.passenger.persistence.dao.PassengerDao;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.service.converter.PassengerServiceConverter;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    private static final String PASSENGER_ID = "passengerId";
    @Mock
    private PassengerDao passengerDao;

    @Mock
    private PassengerServiceConverter passengerServiceConverter;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    private PassengerEntity passengerEntity;

    private PassengerSO passengerSO;

    @BeforeEach
    void setUp() {
        passengerEntity = PassengerEntity.builder()
                .passengerId("PASSENGER-5gsel4brastc7gvl4be38cvd2a")
                .passengerRRN("910427-1111111")
                .name("길동")
                .type(ApplicationConstants.PASSENGER_TYPE_VIP)
                .build();

        passengerSO = PassengerSO.builder()
                .passengerId("PASSENGER-5gsel4brastc7gvl4be38cvd2a")
                .passengerRRN("910427-1111111")
                .name("길동")
                .type(ApplicationConstants.PASSENGER_TYPE_VIP)
                .build();
    }


    @Test
    void getListAllPassengerList() {
        // given
        List<PassengerEntity> mockPassengerEntities = Arrays.asList(passengerEntity);
        BDDMockito.given(passengerDao.getAllPassengers()).willReturn(mockPassengerEntities);

        List<PassengerSO> expectedPassengerSOs = Arrays.asList(passengerSO);
        BDDMockito.given(passengerServiceConverter.toPassengerSOList(mockPassengerEntities)).willReturn(expectedPassengerSOs);

        // when
        List<PassengerSO> actualPassengerSOs = passengerService.getListAllPassengerList();

        // then
        BDDMockito.then(passengerDao).should(Mockito.times(1)).getAllPassengers();
        BDDMockito.then(passengerServiceConverter).should(Mockito.times(1)).toPassengerSOList(mockPassengerEntities);
        Assertions.assertEquals(expectedPassengerSOs, actualPassengerSOs);
    }

    @Test
    void getPassengerDetailByPassengerId() {
        // given
        BDDMockito.given(passengerDao.getPassengerById(PASSENGER_ID)).willReturn(passengerEntity);
        BDDMockito.given(passengerServiceConverter.toPassengerSO(passengerEntity)).willReturn(passengerSO);

        // when
        PassengerSO ret = passengerService.getPassengerDetailByPassengerId(PASSENGER_ID);

        // then
        Assertions.assertEquals(ret, passengerSO);
    }

    @Test
    void updatePassengerType() {
        // given
        BDDMockito.given(passengerDao.updatePassengerType(PASSENGER_ID, ApplicationConstants.PASSENGER_TYPE_VIP)).willReturn(passengerEntity);
        BDDMockito.given((passengerServiceConverter.toPassengerSO(passengerEntity))).willReturn(passengerSO);

        // when
        PassengerSO ret = passengerService.updatePassengerType(PASSENGER_ID, ApplicationConstants.PASSENGER_TYPE_VIP);

        // then
        Assertions.assertEquals(ret, passengerSO);
    }

    @Test
    void deletePassenger() {
        // given
        List<String> passengerIds = new ArrayList<>();
        passengerIds.add(PASSENGER_ID);
        BDDMockito.given(passengerDao.updateAsDeletedByPassengerIds(passengerIds)).willReturn(passengerIds.size());

        // when
        int ret = passengerService.deletePassenger(passengerIds);

        // then
        Assertions.assertEquals(ret, passengerIds.size());
    }

    @Test
    void createPassengerList() {
        // given
        PassengerCreateRequestSO passengerCreateRequestSO = new PassengerCreateRequestSO();
        List<PassengerSO> passengerSoList = new ArrayList<>();
        passengerSoList.add(passengerSO);
        passengerCreateRequestSO.setPassengerSOList(passengerSoList);

        List<PassengerEntity> passengerEntityList = new ArrayList<>();
        passengerEntityList.add(passengerEntity);
        BDDMockito.given(passengerDao.saveAllPassengerList(Mockito.anyList())).willReturn(passengerEntityList);
        BDDMockito.given(passengerServiceConverter.toPassengerSOList(passengerEntityList)).willReturn(passengerSoList);

        // when
        List<PassengerSO> ret = passengerService.createPassengerList(passengerCreateRequestSO);

        // then
        Assertions.assertEquals(ret, passengerSoList);
    }
}