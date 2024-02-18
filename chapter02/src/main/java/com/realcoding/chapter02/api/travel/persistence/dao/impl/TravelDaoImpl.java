package com.realcoding.chapter02.api.travel.persistence.dao.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.code.DomainEnum;
import com.realcoding.chapter02.api.common.util.RCIdGenerator;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.travel.persistence.dao.TravelDao;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.persistence.repository.FlightRepository;
import com.realcoding.chapter02.api.travel.persistence.repository.PassengerJPARepository;
import com.realcoding.chapter02.api.travel.persistence.repository.TravelRepository;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSODetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TravelDaoImpl implements TravelDao {

    private final TravelRepository travelRepository;
    private final PassengerJPARepository passengerRepository;
    private final FlightRepository flightRepository;

    @Override
    public List<TravelEntity> saveAllTravelList(TravelCreateRequestSO travelCreateRequestSO) {
        List<TravelEntity> travelEntityList = new ArrayList<>();
        try {
            for (TravelCreateRequestSODetail soDetail : travelCreateRequestSO.getTravelDetailSOList()) {
                PassengerEntity passengerEntity = passengerRepository.findByPassengerId(soDetail.getPassengerSO().getPassengerId());
                FlightEntity flightEntity = flightRepository.findByFlightId(soDetail.getFlightSO().getFlightId());
                TravelEntity travelEntity = TravelEntity.builder()
                        .travelId(RCIdGenerator.generateId(DomainEnum.TRAVEL))
                        .status(ApplicationConstants.CREATED)
                        .passenger(passengerEntity)
                        .flight(flightEntity)
                        .build();
                travelEntityList.add(travelEntity);
            }
            travelEntityList = travelRepository.saveAll(travelEntityList);
        } catch (RuntimeException re) {
            log.error("TravelDaoImpl > saveAllTravelList has error! ", re);
        }
        return travelEntityList;
    }
}
