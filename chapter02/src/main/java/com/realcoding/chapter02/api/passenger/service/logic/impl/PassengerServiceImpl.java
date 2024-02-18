package com.realcoding.chapter02.api.passenger.service.logic.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.code.DomainEnum;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.common.util.RCIdGenerator;
import com.realcoding.chapter02.api.passenger.persistence.dao.PassengerDao;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.service.converter.PassengerServiceConverter;
import com.realcoding.chapter02.api.passenger.service.logic.PassengerService;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    private final PassengerDao passengerDao;
    private final PassengerServiceConverter passengerServiceConverter;

    @Override
    public List<PassengerSO> getListAllPassengerList() {
        List<PassengerEntity> passengerEntityList = passengerDao.getAllPassengers();
        List<PassengerSO> passengerSOList = passengerServiceConverter.toPassengerSOList(passengerEntityList);
        return passengerSOList;
    }

    @Override
    public PassengerSO getPassengerDetailByPassengerId(String passengerId) {
        PassengerEntity passengerEntity = passengerDao.getPassengerById(passengerId);
        PassengerSO passengerSO = passengerServiceConverter.toPassengerSO(passengerEntity);
        return passengerSO;
    }

    @Override
    public PassengerSO updatePassengerType(String passengerId, String type) {
        PassengerEntity passengerEntity = passengerDao.updatePassengerType(passengerId, type);
        PassengerSO passengerSO = passengerServiceConverter.toPassengerSO(passengerEntity);
        return passengerSO;
    }

    @Override
    public int deletePassenger(List<String> passengerIds) {
        int ret = passengerDao.updateAsDeletedByPassengerIds(passengerIds);
        if (ret != passengerIds.size()) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, "정상적으로 삭제되지 않은 항공편이 있습니다.");
        }
        return ret;
    }

    @Override
    public List<PassengerSO> createPassengerList(PassengerCreateRequestSO passengerCreateRequestSO) {
        List<PassengerEntity> passengerEntityList = new ArrayList<>();
        for (PassengerSO passengerSO : passengerCreateRequestSO.getPassengerSOList()) {
            passengerEntityList.add(PassengerEntity.builder()
                    .passengerId(RCIdGenerator.generateId(DomainEnum.PASSENGER))
                    .name(passengerSO.getName())
                    .passengerRRN(passengerSO.getPassengerRRN())
                    .type(passengerSO.getType())
                    .status(ApplicationConstants.CREATED)
                    .createdBy(ApplicationConstants.SYSTEM_ID)
                    .createdDt(LocalDateTime.now())
                    .modifiedBy(ApplicationConstants.SYSTEM_ID)
                    .modifiedDt(LocalDateTime.now())
                    .build());
        }
        passengerEntityList = passengerDao.saveAllPassengerList(passengerEntityList);
        List<PassengerSO> passengerSOList = passengerServiceConverter.toPassengerSOList(passengerEntityList);
        return passengerSOList;
    }
}
