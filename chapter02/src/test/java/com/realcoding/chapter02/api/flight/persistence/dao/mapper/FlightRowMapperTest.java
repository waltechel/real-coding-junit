package com.realcoding.chapter02.api.flight.persistence.dao.mapper;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightRowMapperTest {


    @Test
    public void testMapRow() throws Exception {
        // Mock ResultSet
        ResultSet rs = Mockito.mock(ResultSet.class);
        when(rs.getString("FLIGHT_ID")).thenReturn("F123");
        when(rs.getString("FLIGHT_NAME")).thenReturn("Test Flight");
        when(rs.getString("STATUS")).thenReturn("On Time");
        when(rs.getString("TYPE")).thenReturn("Commercial");
        when(rs.getString("SOURCE_NAME")).thenReturn("Airport A");
        when(rs.getString("TARGET_NAME")).thenReturn("Airport B");
        LocalDateTime now = LocalDateTime.now();
        when(rs.getObject("CREATED_DT", LocalDateTime.class)).thenReturn(now);
        when(rs.getObject("MODIFIED_DT", LocalDateTime.class)).thenReturn(now);

        // Create an instance of FlightRowMapper and use it to map the row
        FlightRowMapper mapper = new FlightRowMapper();
        FlightEntity flight = mapper.mapRow(rs, 1);

        // Assert the results
        assertEquals("F123", flight.getFlightId());
        assertEquals("Test Flight", flight.getFlightName());
        assertEquals("On Time", flight.getStatus());
        assertEquals("Commercial", flight.getType());
        assertEquals("Airport A", flight.getSourceName());
        assertEquals("Airport B", flight.getTargetName());
        assertEquals(now, flight.getCreatedDt());
        assertEquals(now, flight.getModifiedDt());

    }
}
