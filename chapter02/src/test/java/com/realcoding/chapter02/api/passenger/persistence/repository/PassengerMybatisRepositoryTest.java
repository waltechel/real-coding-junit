package com.realcoding.chapter02.api.passenger.persistence.repository;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.config.TestBeanConfig;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql", "/data-test.sql"}) // 데이터베이스 스키마 및 테스트 데이터를 로드합니다.
@Import(TestBeanConfig.class)
class PassengerMybatisRepositoryTest {

    @Autowired
    private PassengerMybatisRepository passengerMybatisRepository;

    @Autowired
    @Qualifier("vipPassengerEntity")
    private PassengerEntity vipPassengerEntity;

    @Autowired
    @Qualifier("regularPassengerEntity")
    private PassengerEntity regularPassengerEntity;

    @Test
    void getAllPassengers() {
        List<PassengerEntity> passengerEntityList = passengerMybatisRepository.getAllPassengers();
        Assertions.assertEquals(passengerEntityList.size(), 2);
    }

    @Test
    void getPassengerById() {
        String vipPassengerId = "PASSENGER-v8a1uk039ovuj6ikokbqe0qid2";
        String regularPassengerId = "PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp";

        PassengerEntity regularPassenger = passengerMybatisRepository.getPassengerById(regularPassengerId);
        PassengerEntity vipPassenger = passengerMybatisRepository.getPassengerById(vipPassengerId);
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertEquals(vipPassengerId, vipPassenger.getPassengerId()),
                () -> Assertions.assertEquals(regularPassengerId, regularPassenger.getPassengerId())
        );
    }

    @Test
    void saveAllPassengerList() {
        List<PassengerEntity> passengers = Arrays.asList(
                regularPassengerEntity,
                vipPassengerEntity
        );
        passengerMybatisRepository.saveAllPassengerList(passengers);
        List<PassengerEntity> ret = passengerMybatisRepository.getAllPassengersByPassengerIds(passengers.stream().map(a -> a.getPassengerId()).collect(Collectors.toList()));
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertTrue(ret.containsAll(passengers) && passengers.containsAll(ret))
        );
    }

    @Test
    void getAllPassengersByPassengerIds() {
        List<String> passengerIds = Arrays.asList(
                "PASSENGER-v8a1uk039ovuj6ikokbqe0qid2",
                "PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp"
        );

        List<PassengerEntity> ret = passengerMybatisRepository.getAllPassengersByPassengerIds(passengerIds);
        Assertions.assertAll("결괏값 검증", () -> Assertions.assertEquals(ret.size(), 2));

    }

    @Test
    void updatePassengerType() {
        String passengerId = "PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp";
        passengerMybatisRepository.updatePassengerType(passengerId, ApplicationConstants.PASSENGER_TYPE_VIP);
        PassengerEntity passengerEntity = passengerMybatisRepository.getPassengerById(passengerId);
        Assertions.assertEquals(passengerEntity.getType(), ApplicationConstants.PASSENGER_TYPE_VIP);
    }

    @Test
    void updateAsDeletedByPassengerIds() {
        List<String> passengerIds = Arrays.asList(
                "PASSENGER-v8a1uk039ovuj6ikokbqe0qid2",
                "PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp"
        );
        int ret = passengerMybatisRepository.updateAsDeletedByPassengerIds(passengerIds);
        Assertions.assertEquals(ret, 2);
    }

    @Test
    void getListAllPassengersByPassengerIds() {
        List<String> passengerIds = Arrays.asList(
                "PASSENGER-v8a1uk039ovuj6ikokbqe0qid2",
                "PASSENGER-5q1h51aa9mc5p2fb7ki86t78fp"
        );
        List<PassengerEntity> ret = passengerMybatisRepository.getAllPassengersByPassengerIds(passengerIds);
        Assertions.assertTrue(passengerIds.containsAll(ret.stream().map(PassengerEntity::getPassengerId).collect(Collectors.toList()))
                && ret.stream().map(PassengerEntity::getPassengerId).collect(Collectors.toList()).containsAll(passengerIds)
        );
    }
}