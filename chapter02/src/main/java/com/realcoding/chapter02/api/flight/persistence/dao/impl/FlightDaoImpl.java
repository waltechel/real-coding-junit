package com.realcoding.chapter02.api.flight.persistence.dao.impl;

import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.persistence.dao.mapper.FlightRowMapper;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightDaoImpl implements FlightDao {

    // query part
    private static final String GET_ALL_FLIGHT_SQL = "select * from rc_flight where status != 'DELETED'";
    private static final String GET_ALL_FLIGHT_BY_FLIGHT_IDS_SQL = "select * from rc_flight where status != 'DELETED' and flight_id in ( :flightIds )";
    private static final String GET_FLIGHT_BY_FLIGHT_ID_SQL = "select * from rc_flight where flight_id = :flightId and status != 'DELETED'";
    private static final String INSERT_FLIGHT_SQL = "INSERT INTO rc_flight (flight_id, flight_name, source_name, target_name, status, created_dt, created_by, modified_dt, modified_by) " + "VALUES (:flightId, :flightName, :sourceName, :targetName, :status, :createdDt, :createdBy, :modifiedDt, :modifiedBy)";
    private static final String UPDATE_FLIGHT_STATUS_TO_DELETED_SQL = "UPDATE rc_flight SET status = 'DELETED' WHERE flight_id in ( :flightIds ) ";

    // autowired
    private final FlightRowMapper FLIGHT_ROW_MAPPER;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<FlightEntity> getListAllFlight() {
        List<FlightEntity> ret = new ArrayList<>();
        try {
            ret = jdbcTemplate.query(GET_ALL_FLIGHT_SQL, FLIGHT_ROW_MAPPER);
        } catch (RuntimeException re) {
            log.error("FlightDaoImpl > getListAllFlight has error!", re);
        }
        return ret;
    }

    @Override
    public List<FlightEntity> saveAllFlightList(List<FlightEntity> flightEntityList) {
        List<FlightEntity> ret = new ArrayList<>();
        try {
            SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(flightEntityList.toArray());
            namedParameterJdbcTemplate.batchUpdate(INSERT_FLIGHT_SQL, batchArgs);
            ret = flightEntityList;
        } catch (RuntimeException re) {
            log.error("FlightDaoImpl > saveAllFlightList has error!", re);
            ret = new ArrayList<>();
        }
        return ret; // 삽입된 엔티티 목록을 반환합니다.
    }

    @Override
    public FlightEntity getFlightEntityByFlightId(String flightId) {
        FlightEntity flightEntity = new FlightEntity();
        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("flightId", flightId);
            flightEntity = namedParameterJdbcTemplate.queryForObject(GET_FLIGHT_BY_FLIGHT_ID_SQL, parameters, FLIGHT_ROW_MAPPER);
        } catch (RuntimeException re) {
            log.error("FlightDaoImpl > getListAllFlight has error!", re);
        }
        return flightEntity;
    }

    @Override
    public List<FlightEntity> getListAllFlightByFlightIds(List<String> flightIds) {
        List<FlightEntity> flightEntities = new ArrayList<>();
        try {
            // flightIds를 SqlParameterSource에 맵핑
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("flightIds", flightIds);
            // 쿼리 실행
            flightEntities = namedParameterJdbcTemplate.query(GET_ALL_FLIGHT_BY_FLIGHT_IDS_SQL, parameters, FLIGHT_ROW_MAPPER);
        } catch (RuntimeException re) {
            log.error("FlightDaoImpl > getListAllFlightByFlightIds has error!", re);
        }
        return flightEntities;
    }

    @Override
    public int updateAsDeletedByFlightIds(List<String> flightIds) {
        int ret = 0;
        try {
            // 각 flightId에 대해 업데이트를 수행
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("flightIds", flightIds);
            ret = namedParameterJdbcTemplate.update(UPDATE_FLIGHT_STATUS_TO_DELETED_SQL, parameters);
        } catch (RuntimeException re) {
            log.error("FlightDaoImpl > deleteFlightList has error!", re);
            // 필요한 경우 예외를 던지거나 다른 처리를 할 수 있습니다.
        }
        return ret;
    }

}
