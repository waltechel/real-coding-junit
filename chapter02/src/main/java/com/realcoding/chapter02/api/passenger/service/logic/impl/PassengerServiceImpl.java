package com.realcoding.chapter02.api.passenger.service.logic.impl;

import com.realcoding.chapter02.api.passenger.service.logic.PassengerService;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {
    @Override
    public List<PassengerSO> getListAllPassengerList() {
        return null;
    }

    @Override
    public PassengerSO getPassengerDetailByPassengerId(String passengerId) {
        return null;
    }

    @Override
    public PassengerSO updatePassengerType(String passengerId, String type) {
        return null;
    }

    @Override
    public void deletePassenger(List<String> passengerIds) {

    }

    @Override
    public List<PassengerSO> createPassengerList(PassengerCreateRequestSO passengerCreateRequestSO) {
        return null;
    }
}
