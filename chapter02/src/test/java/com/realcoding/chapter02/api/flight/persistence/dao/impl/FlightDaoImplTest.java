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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql", "/data-test.sql"}) // 데이터베이스 스키마 및 테스트 데이터를 로드합니다.
@Import(TestBeanConfig.class)
class FlightDaoImplTest {

    private static final String GET_ALL_FLIGHT_SQL = "select * from rc_flight where status != 'DELETED'";
    private static final String GET_ALL_FLIGHT_BY_FLIGHT_IDS_SQL = "select * from rc_flight where status != 'DELETED' and flight_id in ( :flightIds )";
    private static final String GET_FLIGHT_BY_FLIGHT_ID_SQL = "select * from rc_flight where flight_id = :flightId and status != 'DELETED'";
    private static final String INSERT_FLIGHT_SQL = "INSERT INTO rc_flight (flight_id, flight_name, source_name, target_name, type, status, created_dt, created_by, modified_dt, modified_by) " + "VALUES (:flightId, :flightName, :sourceName, :targetName, :type, :status, :createdDt, :createdBy, :modifiedDt, :modifiedBy)";
    private static final String UPDATE_FLIGHT_STATUS_TO_DELETED_SQL = "UPDATE rc_flight SET status = 'DELETED' WHERE flight_id in ( :flightIds ) ";

    private FlightRowMapper FLIGHT_ROW_MAPPER = new FlightRowMapper();
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("businessFlightEntity")
    private FlightEntity businessFlightEntity;
    @Autowired
    @Qualifier("economyFlightEntity")
    private FlightEntity economyFlightEntity;

    @Test
    void getListAllFlight() {
        List<FlightEntity> flightEntityList = jdbcTemplate.query(GET_ALL_FLIGHT_SQL, FLIGHT_ROW_MAPPER);
        Assertions.assertEquals(flightEntityList.size(), 2);
    }

    @Test
    void saveAllFlightList() {
        List<FlightEntity> flightEntityList = new ArrayList<>();
        flightEntityList.add(economyFlightEntity);
        flightEntityList.add(businessFlightEntity);
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(flightEntityList.toArray());
        int[] ret = namedParameterJdbcTemplate.batchUpdate(INSERT_FLIGHT_SQL, batchArgs);
        Assertions.assertAll("결괏값 검증",
                () -> Assertions.assertEquals(ret[0], 1),
                () -> Assertions.assertEquals(ret[1], 1)
        );
    }

    @Test
    void getFlightEntityByFlightId() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("flightId", "FLIGHT-ov3bks8mbqhbbggekduevduomi");
        FlightEntity flightEntity = namedParameterJdbcTemplate.queryForObject(GET_FLIGHT_BY_FLIGHT_ID_SQL, parameters, FLIGHT_ROW_MAPPER);
        Assertions.assertNotNull(flightEntity);
    }

    @Test
    void getListAllFlightByFlightIds() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        List<String> flightIds = new ArrayList<>();
        flightIds.add("FLIGHT-ov3bks8mbqhbbggekduevduomi");
        flightIds.add("FLIGHT-bp2cvqov33019rpjik00cu497g");
        parameters.addValue("flightIds", flightIds);
        // 쿼리 실행
        List<FlightEntity> flightEntities = namedParameterJdbcTemplate.query(GET_ALL_FLIGHT_BY_FLIGHT_IDS_SQL, parameters, FLIGHT_ROW_MAPPER);
        Assertions.assertEquals(flightEntities.size(), 2);
    }

    @Test
    void updateAsDeletedByFlightIds() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        List<String> flightIds = new ArrayList<>();
        flightIds.add("FLIGHT-ov3bks8mbqhbbggekduevduomi");
        flightIds.add("FLIGHT-bp2cvqov33019rpjik00cu497g");
        parameters.addValue("flightIds", flightIds);
        // 쿼리 실행
        int ret = namedParameterJdbcTemplate.update(UPDATE_FLIGHT_STATUS_TO_DELETED_SQL, parameters);
        Assertions.assertEquals(ret, 2);
    }
}