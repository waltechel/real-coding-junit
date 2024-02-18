package com.realcoding.chapter02.api.passenger.persistence.repository;

import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PassengerRepository {
    List<PassengerEntity> getAllPassengers();

    PassengerEntity getPassengerById(String passengerId);

    void saveAllPassengerList(List<PassengerEntity> passengerEntityList);

    List<PassengerEntity> getAllPassengersByPassengerIds(List<String> collect);

    void updatePassengerType(String passengerId, String type);

    int updateAsDeletedByPassengerIds(List<String> passengerIds);

    List<PassengerEntity> getListAllPassengersByPassengerIds(List<String> passengerIds);
}
