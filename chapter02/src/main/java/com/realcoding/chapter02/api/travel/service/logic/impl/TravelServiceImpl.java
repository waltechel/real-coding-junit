package com.realcoding.chapter02.api.travel.service.logic.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.code.DomainEnum;
import com.realcoding.chapter02.api.common.util.RCIdGenerator;
import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.service.converter.FlightServiceConverter;
import com.realcoding.chapter02.api.passenger.persistence.dao.PassengerDao;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.travel.persistence.dao.TravelDao;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.service.converter.TravelServiceConverter;
import com.realcoding.chapter02.api.travel.service.logic.TravelService;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSODetail;
import com.realcoding.chapter02.api.travel.service.so.TravelSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TravelServiceImpl implements TravelService {

    private final TravelDao travelDao;
    private final PassengerDao passengerDao;
    private final FlightDao flightDao;
    private final TravelServiceConverter travelServiceConverter;

    @Override
    public List<TravelSO> getListAllTravel() {
        return null;
    }

    @Override
    public List<TravelSO> createTravelList(TravelCreateRequestSO travelCreateRequestSO) {
        List<TravelEntity> travelEntityList = new ArrayList<>();
        for (TravelCreateRequestSODetail soDetail : travelCreateRequestSO.getTravelDetailSOList()) {
            PassengerEntity passengerEntity = passengerDao.getPassengerById(soDetail.getPassengerSO().getPassengerId());
            FlightEntity flightEntity = flightDao.getFlightEntityByFlightId(soDetail.getFlightSO().getFlightId());
            TravelEntity travelEntity = TravelEntity.builder()
                    .travelId(RCIdGenerator.generateId(DomainEnum.TRAVEL))
                    .status(ApplicationConstants.CREATED)
                    .passenger(passengerEntity)
                    .flight(flightEntity)
                    .build();
            travelEntityList.add(travelEntity);
        }
        travelEntityList = travelDao.saveAllTravelList(travelEntityList);
        List<TravelSO> travelSOList = travelServiceConverter.toTravelSOList(travelEntityList);
        return travelSOList;
    }

    @Override
    public TravelSO getTravelDetailByTravelId(String travelId) {
        return null;
    }

    @Override
    public int deleteTravelList(List<String> travelIds) {
        return 0;
    }
}
