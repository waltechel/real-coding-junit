package com.realcoding.chapter02.api.travel.persistence.dao.impl;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.code.DomainEnum;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
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

    @Override
    public List<TravelEntity> getListAllTravel() {
        List<TravelEntity> ret = new ArrayList<>();
        try {
            ret = travelRepository.findAllTravelList();
        } catch (RuntimeException re) {
            log.error("TravelDaoImpl > getListAllTravel has error! ", re);
        }
        return ret;
    }

    @Override
    public TravelEntity getTravelDetailByTravelId(String travelId) {
        TravelEntity travelEntity;
        try {
            travelEntity = travelRepository.findByTravelId(travelId);
        } catch (RuntimeException re) {
            log.error("TravelDaoImpl > getTravelDetailByTravelId has error! ", re);
            throw new CustomException(ErrorCode.NOT_FOUND, "여행 정보를 찾을 수 없습니다.");
        }
        return travelEntity;
    }

    @Override
    public int updateAsDeletedByTravelIds(List<String> travelIds) {
        int ret = 0;
        try {
            List<TravelEntity> travelEntityList = travelRepository.findAllByTravelIds(travelIds);
            for(TravelEntity travelEntity : travelEntityList){
                travelEntity.setStatus(ApplicationConstants.DELETED);
            }
            travelEntityList = travelRepository.saveAll(travelEntityList);
            ret = travelEntityList.size();
        } catch (RuntimeException re) {
            log.error("TravelDaoImpl > updateAsDeletedByTravelIds has error! ", re);
        }
        return ret;
    }
}
