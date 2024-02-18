package com.realcoding.chapter02.api.passenger.service.logic;

import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;

import java.util.List;

public interface PassengerService {
    List<PassengerSO> getListAllPassengerList();

    PassengerSO getPassengerDetailByPassengerId(String passengerId);

    PassengerSO updatePassengerType(String passengerId, String type);

    int deletePassenger(List<String> passengerIds);

    List<PassengerSO> createPassengerList(PassengerCreateRequestSO passengerCreateRequestSO);
}
