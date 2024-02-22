package com.realcoding.chapter02.api.flight.persistence.dao.impl;

import com.realcoding.chapter02.api.common.config.TestBeanConfig;
import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.persistence.dao.mapper.FlightRowMapper;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql", "/data-test.sql"}) // 데이터베이스 스키마 및 테스트 데이터를 로드합니다.
@Import(TestBeanConfig.class)
class FlightDaoImplTest {

	private FlightRowMapper FLIGHT_ROW_MAPPER;
	private FlightDao flightDao;

	@Autowired
	public FlightDaoImplTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
		this.FLIGHT_ROW_MAPPER = new FlightRowMapper();
		this.flightDao = new FlightDaoImpl(FLIGHT_ROW_MAPPER, namedParameterJdbcTemplate, jdbcTemplate);
	}

	@Autowired
	@Qualifier("businessFlightEntity")
	private FlightEntity businessFlightEntity;
	@Autowired
	@Qualifier("economyFlightEntity")
	private FlightEntity economyFlightEntity;

	@Test
	void getListAllFlight() {
		List<FlightEntity> flightEntityList = flightDao.getListAllFlight();
		Assertions.assertEquals(flightEntityList.size(), 2);
	}

	@Test
	void saveAllFlightList() {
		List<FlightEntity> flightEntityList = new ArrayList<>();
		flightEntityList.add(economyFlightEntity);
		flightEntityList.add(businessFlightEntity);
		List<FlightEntity> ret = flightDao.saveAllFlightList(flightEntityList);
		Assertions.assertAll("결괏값 검증",
				() -> Assertions.assertEquals(ret, flightEntityList),
				() -> Assertions.assertEquals(ret.size(), flightEntityList.size())
		);
	}

	@Test
	void getFlightEntityByFlightId() {
		FlightEntity flightEntity = flightDao.getFlightEntityByFlightId("FLIGHT-ov3bks8mbqhbbggekduevduomi");
		Assertions.assertNotNull(flightEntity);
	}

	@Test
	void getListAllFlightByFlightIds() {
		List<String> flightIds = Arrays.asList(
				"FLIGHT-ov3bks8mbqhbbggekduevduomi",
				"FLIGHT-bp2cvqov33019rpjik00cu497g"
		);
		// 쿼리 실행
		List<FlightEntity> flightEntities = flightDao.getListAllFlightByFlightIds(flightIds);
		Assertions.assertEquals(flightEntities.size(), 2);
	}

	@Test
	void updateAsDeletedByFlightIds() {
		List<String> flightIds = Arrays.asList(
				"FLIGHT-ov3bks8mbqhbbggekduevduomi",
				"FLIGHT-bp2cvqov33019rpjik00cu497g"
		);
		// 쿼리 실행
		int ret = flightDao.updateAsDeletedByFlightIds(flightIds);
		Assertions.assertEquals(ret, 2);
	}
}
