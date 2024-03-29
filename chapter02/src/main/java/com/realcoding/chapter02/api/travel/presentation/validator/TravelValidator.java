package com.realcoding.chapter02.api.travel.presentation.validator;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.flight.service.logic.FlightService;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import com.realcoding.chapter02.api.passenger.service.logic.PassengerService;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import com.realcoding.chapter02.api.travel.persistence.dao.TravelDao;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequest;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequestDetail;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelDeleteRequest;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSODetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TravelValidator {

    private final TravelDao travelDao;
    private final PassengerService passengerService;
    private final FlightService flightService;

    public TravelCreateRequestSO createRequestValidate(TravelCreateRequest travelCreateRequest) {

        if (travelCreateRequest == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "적절하지 않은 여행 요청입니다.");
        }
        if (CollectionUtils.isEmpty(travelCreateRequest.getTravelDetailList())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "적절하지 않은 여행 요청입니다.");
        }
        for (TravelCreateRequestDetail travelCreateRequestDetail : travelCreateRequest.getTravelDetailList()) {
            if (StringUtils.isEmpty(travelCreateRequestDetail.getFlightId())) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "항공편 아이디가 없습니다");
            }
            if (StringUtils.isEmpty(travelCreateRequestDetail.getPassengerId())) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "승객 아이디가 없습니다");
            }
        }

        TravelCreateRequestSO travelCreateRequestSO = new TravelCreateRequestSO();
        List<TravelCreateRequestSODetail> travelCreateRequestSODetailList = new ArrayList<>();
        for (TravelCreateRequestDetail travelCreateRequestDetail : travelCreateRequest.getTravelDetailList()) {
            TravelCreateRequestSODetail soDetail = new TravelCreateRequestSODetail();
            FlightSO flightSO = flightService.getFlightDetailByFlightId(travelCreateRequestDetail.getFlightId());
            if (flightSO == null) {
                throw new CustomException(ErrorCode.NOT_FOUND, "존재하지 않는 항공편입니다.");
            }
            PassengerSO passengerSO = passengerService.getPassengerDetailByPassengerId(travelCreateRequestDetail.getPassengerId());
            if (passengerSO == null) {
                throw new CustomException(ErrorCode.NOT_FOUND, "존재하지 않는 승객입니다.");
            }
            // 비즈니스 항공편에 일반 승객은 여행할 수 없다
            if (StringUtils.equals(ApplicationConstants.FLIGHT_TYPE_BUSINESS, flightSO.getType()) &&
                    StringUtils.equals(ApplicationConstants.PASSENGER_TYPE_REGULAR, passengerSO.getType())) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "비즈니스 항공편에 일반 승객은 여행할 수 없습니다.");
            }
            soDetail.setFlightSO(flightSO);
            soDetail.setPassengerSO(passengerSO);
            travelCreateRequestSODetailList.add(soDetail);
        }

        travelCreateRequestSO.setTravelDetailSOList(travelCreateRequestSODetailList);
        return travelCreateRequestSO;
    }

    public List<String> deleteRequestValidate(TravelDeleteRequest travelDeleteRequest) {
        if (CollectionUtils.isEmpty(travelDeleteRequest.getTravelIds())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "삭제할 여행 아이디가 없습니다.");
        }
        for (String travelId : travelDeleteRequest.getTravelIds()) {
            if (StringUtils.isEmpty(travelId)) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "삭제할 여행 아이디가 없습니다.");
            }
        }
        List<String> travelIds = travelDeleteRequest.getTravelIds();

        List<TravelEntity> travelEntityList = travelDao.getListAllTravelByTravelIds(travelIds);
        if (travelEntityList.size() != travelIds.size()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "적절하지 않은 여행 아이디가 있습니다");
        }

        for (TravelEntity travelEntity : travelEntityList) {
            if (StringUtils.equals(ApplicationConstants.FLIGHT_TYPE_BUSINESS, travelEntity.getFlight().getType())) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "비즈니스 항공편을 이용한 여행은 삭제가 불가능합니다");
            }
            if (StringUtils.equals(ApplicationConstants.PASSENGER_TYPE_VIP, travelEntity.getPassenger().getType())) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "VIP 승객의 여행은 삭제가 불가능합니다");
            }
        }

        return travelIds;

    }
}
