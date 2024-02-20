package com.realcoding.chapter02.api.common.config;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBeanConfig {

    @Bean
    @Qualifier("economyFlightEntity")
    public FlightEntity economyFlightEntity() {
        return FlightEntity.builder()
                .flightId("FLIGHT-kmhjs0dilg6g7hevlp0o6vfrvf")
                .flightName("KR234")
                .sourceName("서울")
                .targetName("부산")
                .type(ApplicationConstants.FLIGHT_TYPE_ECONOMY)
                .build();
    }

    @Bean
    @Qualifier("businessFlightEntity")
    public FlightEntity businessFlightEntity() {
        return FlightEntity.builder()
                .flightId("FLIGHT-mlv6gcutsiap6vnqinjg7ntmfm")
                .flightName("US999")
                .sourceName("인천")
                .targetName("캘리포니아")
                .type(ApplicationConstants.FLIGHT_TYPE_BUSINESS)
                .build();
    }

    @Bean
    @Qualifier("economyFlightSO")
    public FlightSO economyFlightSO() {
        return FlightSO.builder()
                .flightId("FLIGHT-kmhjs0dilg6g7hevlp0o6vfrvf")
                .flightName("KR234")
                .sourceName("서울")
                .targetName("부산")
                .type(ApplicationConstants.FLIGHT_TYPE_ECONOMY)
                .build();
    }

    @Bean
    @Qualifier("businessFlightSO")
    public FlightSO businessFlightSO() {
        return FlightSO.builder()
                .flightId("FLIGHT-mlv6gcutsiap6vnqinjg7ntmfm")
                .flightName("US999")
                .sourceName("인천")
                .targetName("캘리포니아")
                .type(ApplicationConstants.FLIGHT_TYPE_BUSINESS)
                .build();
    }

    @Bean
    @Qualifier("economyFlightDTO")
    public FlightDTO economyFlightDTO() {
        return FlightDTO.builder()
                .flightId("FLIGHT-kmhjs0dilg6g7hevlp0o6vfrvf")
                .flightName("KR234")
                .sourceName("서울")
                .targetName("부산")
                .type(ApplicationConstants.FLIGHT_TYPE_ECONOMY)
                .build();
    }

    @Bean
    @Qualifier("businessFlightDTO")
    public FlightDTO businessFlightDTO() {
        return FlightDTO.builder()
                .flightId("FLIGHT-mlv6gcutsiap6vnqinjg7ntmfm")
                .flightName("US999")
                .sourceName("인천")
                .targetName("캘리포니아")
                .type(ApplicationConstants.FLIGHT_TYPE_BUSINESS)
                .build();
    }

    @Bean
    @Qualifier("regularPassengerEntity")
    public PassengerEntity regularPassengerEntity() {
        return PassengerEntity.builder()
                .passengerId("PASSENGER-fedtkao0vm5s9vtlqdnmg6pr1k")
                .passengerRRN("910427-2222222")
                .name("영희")
                .type(ApplicationConstants.PASSENGER_TYPE_REGULAR)
                .build();
    }

    @Bean
    @Qualifier("vipPassengerEntity")
    public PassengerEntity vipPassengerEntity() {
        return PassengerEntity.builder()
                .passengerId("PASSENGER-5gsel4brastc7gvl4be38cvd2a")
                .passengerRRN("910427-1111111")
                .name("길동")
                .type(ApplicationConstants.PASSENGER_TYPE_VIP)
                .build();
    }

    @Bean
    @Qualifier("regularPassengerSO")
    public PassengerSO regularPassengerSO() {
        return PassengerSO.builder()
                .passengerId("PASSENGER-fedtkao0vm5s9vtlqdnmg6pr1k")
                .passengerRRN("910427-2222222")
                .name("영희")
                .type(ApplicationConstants.PASSENGER_TYPE_REGULAR)
                .build();
    }

    @Bean
    @Qualifier("vipPassengerSO")
    public PassengerSO vipPassengerSO() {
        return PassengerSO.builder()
                .passengerId("PASSENGER-5gsel4brastc7gvl4be38cvd2a")
                .passengerRRN("910427-1111111")
                .name("길동")
                .type(ApplicationConstants.PASSENGER_TYPE_VIP)
                .build();
    }

    @Bean
    @Qualifier("regularPassengerDTO")
    public PassengerDTO regularPassengerDTO() {
        return PassengerDTO.builder()
                .passengerId("PASSENGER-fedtkao0vm5s9vtlqdnmg6pr1k")
                .passengerRRN("910427-2222222")
                .name("영희")
                .type(ApplicationConstants.PASSENGER_TYPE_REGULAR)
                .build();
    }

    @Bean
    @Qualifier("vipPassengerDTO")
    public PassengerDTO vipPassengerDTO() {
        return PassengerDTO.builder()
                .passengerId("PASSENGER-5gsel4brastc7gvl4be38cvd2a")
                .passengerRRN("910427-1111111")
                .name("길동")
                .type(ApplicationConstants.PASSENGER_TYPE_VIP)
                .build();
    }
}
