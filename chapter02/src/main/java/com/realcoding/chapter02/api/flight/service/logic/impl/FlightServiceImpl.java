package com.realcoding.chapter02.api.flight.service.logic.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.code.DomainEnum;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.common.util.RCIdGenerator;
import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.service.converter.FlightServiceConverter;
import com.realcoding.chapter02.api.flight.service.logic.FlightService;
import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightDao flightDao;
    private final FlightServiceConverter flightServiceConverter;

    @Override
    public List<FlightSO> getListAllFlight() {
        List<FlightEntity> flightEntityList = flightDao.getListAllFlight();
        List<FlightSO> flightSOList = flightServiceConverter.toFlightSOList(flightEntityList);
        return flightSOList;
    }

    @Override
    public List<FlightSO> createFlightList(FlightCreateRequestSO flightCreateRequestSO) {

        List<FlightEntity> flightEntityList = new ArrayList<>();
        for (FlightSO flightSO : flightCreateRequestSO.getFlightSOList()) {
            flightEntityList.add(FlightEntity.builder()
                    .flightId(RCIdGenerator.generateId(DomainEnum.FLIGHT))
                    .flightName(flightSO.getFlightName())
                    .sourceName(flightSO.getSourceName())
                    .targetName(flightSO.getTargetName())
                    .type(flightSO.getType())
                    .status(ApplicationConstants.CREATED)
                    .createdBy(ApplicationConstants.SYSTEM_ID)
                    .createdDt(LocalDateTime.now())
                    .modifiedBy(ApplicationConstants.SYSTEM_ID)
                    .modifiedDt(LocalDateTime.now())
                    .build());
        }

        flightEntityList = flightDao.saveAllFlightList(flightEntityList);
        List<FlightSO> flightSOList = flightServiceConverter.toFlightSOList(flightEntityList);
        return flightSOList;
    }

    @Override
    public FlightSO getFlightDetailByFlightId(String flightId) {
        FlightEntity flightEntity = flightDao.getFlightEntityByFlightId(flightId);
        FlightSO flightSO = flightServiceConverter.toFlightSO(flightEntity);
        return flightSO;
    }

    @Override
    public int deleteFlightList(List<String> flightIds) {
        int ret = flightDao.updateAsDeletedByFlightIds(flightIds);
        if (ret != flightIds.size()) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, "정상적으로 삭제되지 않은 항공편이 있습니다.");
        }
        return ret;
    }
}
