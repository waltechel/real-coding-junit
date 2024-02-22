package com.realcoding.chapter02.api.travel.persistence.repository;

import com.realcoding.chapter02.api.common.config.TestBeanConfig;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.persistence.repository.PassengerMybatisRepository;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql", "/data-test.sql"}) // 데이터베이스 스키마 및 테스트 데이터를 로드합니다.
@Import(TestBeanConfig.class)
class TravelRepositoryTest {

    @Autowired
    private TravelRepository travelRepository;

    @Test
    void findAllTravelList() {
        List<TravelEntity> travelEntityList = travelRepository.findAllTravelList();
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertEquals(travelEntityList.size(), 2),
                () -> Assertions.assertNotNull(travelEntityList.get(0).getFlight()),
                () -> Assertions.assertNotNull(travelEntityList.get(0).getPassenger()),
                () -> Assertions.assertNotNull(travelEntityList.get(1).getFlight()), // 관련한 객체까지 같이 조회되었는지 확인
                () -> Assertions.assertNotNull(travelEntityList.get(1).getPassenger())
        );

    }

    @Test
    void findByTravelId() {
        String travelID = "TRAVEL-95bnglap38sds9ovhflc744ben";
        TravelEntity travelEntity = travelRepository.findByTravelId(travelID);
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertEquals(travelID, travelEntity.getTravelId()),
                () -> Assertions.assertNotNull(travelEntity.getFlight()),
                () -> Assertions.assertNotNull(travelEntity.getPassenger())
        );
    }

    @Test
    void findAllByTravelIds() {
        List<String> travelIds = Arrays.asList(
                "TRAVEL-95bnglap38sds9ovhflc744ben",
                "TRAVEL-63testd285p8lfiq6n6kgnkpp9"
        );
        List<TravelEntity> travelEntityList = travelRepository.findAllByTravelIds(travelIds);
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertEquals(travelEntityList.size(), 2),
                () -> Assertions.assertNotNull(travelEntityList.get(0).getFlight()),
                () -> Assertions.assertNotNull(travelEntityList.get(0).getPassenger()),
                () -> Assertions.assertNotNull(travelEntityList.get(1).getFlight()), // 관련한 객체까지 같이 조회되었는지 확인
                () -> Assertions.assertNotNull(travelEntityList.get(1).getPassenger())
        );
    }
}