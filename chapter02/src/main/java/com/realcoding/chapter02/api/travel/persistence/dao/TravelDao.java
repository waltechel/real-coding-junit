package com.realcoding.chapter02.api.travel.persistence.dao;

import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;

import java.util.List;

public interface TravelDao {
    List<TravelEntity> saveAllTravelList(List<TravelEntity> travelEntityList);
}
