package com.realcoding.chapter02.api.flight.service.logic;

import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;

import java.util.List;

public interface FlightService {
    List<FlightSO> getListAllFlight();

    List<FlightSO> createFlightList(FlightCreateRequestSO flightCreateRequestSO);

    FlightSO getFlightDetailByFlightId(String flightId);

    int deleteFlightList(List<String> flightIds);
}
