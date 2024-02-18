package com.realcoding.chapter02.api.travel.service.logic;

import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;
import com.realcoding.chapter02.api.travel.service.so.TravelSO;

import java.util.List;

public interface TravelService {
    List<TravelSO> getListAllTravel();

    List<TravelSO> createTravelList(TravelCreateRequestSO travelCreateRequestSO);

    TravelSO getTravelDetailByTravelId(String travelId);

    int deleteTravelList(List<String> travelIds);
}
