package com.realcoding.chapter02.api.travel.persistence.repository;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface FlightRepository extends JpaRepository<FlightEntity, String> {
    FlightEntity findByFlightId(String flightId);
}
