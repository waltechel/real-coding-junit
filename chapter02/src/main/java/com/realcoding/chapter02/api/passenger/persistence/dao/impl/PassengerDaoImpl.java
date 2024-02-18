package com.realcoding.chapter02.api.passenger.persistence.dao.impl;

import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.passenger.persistence.dao.PassengerDao;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.persistence.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassengerDaoImpl implements PassengerDao {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerEntity> getAllPassengers() {
        List<PassengerEntity> ret = new ArrayList<>();
        try {
            ret = passengerRepository.getAllPassengers();
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > getAllPassengers has error!", re);
        }
        return ret;
    }

    @Override
    public PassengerEntity getPassengerById(String passengerId) {
        PassengerEntity passengerEntity;
        try {
            passengerEntity = passengerRepository.getPassengerById(passengerId);
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > getPassengerById has error!", re);
            throw new CustomException(ErrorCode.NOT_FOUND, "승객 정보를 찾을 수 없습니다.");
        }
        if (passengerEntity == null) {
            throw new CustomException(ErrorCode.NOT_FOUND, "승객 정보를 찾을 수 없습니다.");
        }
        return passengerEntity;
    }

    @Override
    public int updateAsDeletedByPassengerIds(List<String> passengerIds) {
        int ret = 0;
        try {
            ret = passengerRepository.updateAsDeletedByPassengerIds(passengerIds);
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > updateAsDeletedByPassengerIds has error!", re);
        }
        return ret;
    }

    @Override
    public List<PassengerEntity> saveAllPassengerList(List<PassengerEntity> passengerEntityList) {
        List<PassengerEntity> ret = new ArrayList<>();
        try {
            passengerRepository.saveAllPassengerList(passengerEntityList);
            ret = passengerRepository.getAllPassengersByPassengerIds(passengerEntityList.stream().map(PassengerEntity::getPassengerId).collect(Collectors.toList()));
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > saveAllPassengerList has error!", re);
        }
        return ret; // 삽입된 엔티티 목록을 반환합니다.
    }

    @Override
    public PassengerEntity updatePassengerType(String passengerId, String type) {
        PassengerEntity passengerEntity = new PassengerEntity();
        try {
            passengerRepository.updatePassengerType(passengerId, type);
            passengerEntity = getPassengerById(passengerId);
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > updatePassengerType has error!", re);
        }
        return passengerEntity;
    }

    @Override
    public List<PassengerEntity> getListAllPassengersByPassengerIds(List<String> passengerIds) {
        List<PassengerEntity> ret = new ArrayList<>();
        try {
            ret = passengerRepository.getListAllPassengersByPassengerIds(passengerIds);
        } catch (RuntimeException re) {
            log.error("PassengerDaoImpl > getListAllPassengersByPassengerIds has error!", re);
        }
        return ret;
    }
}
