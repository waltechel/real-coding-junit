package com.realcoding.chapter02.api.passenger.persistence.dao;

import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;

import java.util.List;

public interface PassengerDao {
    List<PassengerEntity> getAllPassengers();

    PassengerEntity getPassengerById(String passengerId);

    int updateAsDeletedByPassengerIds(List<String> passengerIds);

    List<PassengerEntity> saveAllPassengerList(List<PassengerEntity> passengerEntityList);

    PassengerEntity updatePassengerType(String passengerId, String type);

    List<PassengerEntity> getListAllPassengersByPassengerIds(List<String> passengerIds);
}
