package com.realcoding.chapter02.api.travel.persistence.dao;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;

import java.util.List;

public interface TravelDao {
    List<TravelEntity> saveAllTravelList(TravelCreateRequestSO travelEntityList);

    List<TravelEntity> getListAllTravel();

    TravelEntity getTravelDetailByTravelId(String travelId);

    int updateAsDeletedByTravelIds(List<String> travelIds);

    List<TravelEntity> getListAllTravelByTravelIds(List<String> flightIds);
}
