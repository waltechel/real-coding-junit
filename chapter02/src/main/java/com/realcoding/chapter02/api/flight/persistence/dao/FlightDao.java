package com.realcoding.chapter02.api.flight.persistence.dao;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;

import java.util.List;

public interface FlightDao {
    List<FlightEntity> getListAllFlight();

    List<FlightEntity> saveAllFlightList(List<FlightEntity> flightEntityList);

    FlightEntity getFlightEntityByFlightId(String flightId);

    List<FlightEntity> getListAllFlightByFlightIds(List<String> flightIds);

    int updateAsDeletedByFlightIds(List<String> flightIds);
}
