package com.realcoding.chapter02.api.flight.persistence.dao.mapper;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Component
@Slf4j
@RequiredArgsConstructor
public class FlightRowMapper implements RowMapper<FlightEntity> {
    @Override
    public FlightEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return FlightEntity.builder()
                .flightId(rs.getString("FLIGHT_ID"))
                .flightName(rs.getString("FLIGHT_NAME"))
                .status(rs.getString("STATUS"))
                .sourceName(rs.getString("SOURCE_NAME"))
                .targetName(rs.getString("TARGET_NAME"))
                .createdDt(rs.getObject("CREATED_DT", LocalDateTime.class))
                .modifiedDt(rs.getObject("MODIFIED_DT", LocalDateTime.class))
                .build();
    }
}
