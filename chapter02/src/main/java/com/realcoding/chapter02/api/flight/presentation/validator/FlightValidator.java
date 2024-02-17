package com.realcoding.chapter02.api.flight.presentation.validator;

import com.realcoding.chapter02.api.flight.persistence.dao.FlightDao;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequestDetail;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightDeleteRequest;
import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightValidator {

    private static final String VALID_FLIGHT_NAME_REGEX = "^[A-Z]{2}\\d{3,4}$"; // 항공편명은 대문자 영문 2자와 숫자 3~4자로 구성되어 있다.
    private static final Pattern pattern = Pattern.compile(VALID_FLIGHT_NAME_REGEX);
    private final FlightDao flightDao;

    public FlightCreateRequestSO createRequestValidate(FlightCreateRequest flightCreateRequest) {
        FlightCreateRequestSO flightCreateRequestSO = new FlightCreateRequestSO();
        List<FlightSO> flightSOList = new ArrayList<>();

        for (FlightCreateRequestDetail flightCreateRequestDetail : flightCreateRequest.getFlightDetailList()) {
            nullcheck(flightCreateRequestDetail);
            FlightSO flightSO = new FlightSO();
            setFlightName(flightSO, flightCreateRequestDetail.getFlightName());
            setSourceName(flightSO, flightCreateRequestDetail.getSourceName());
            setTargetName(flightSO, flightCreateRequestDetail.getTargetName());
            flightSOList.add(flightSO);
        }

        flightCreateRequestSO.setFlightSOList(flightSOList);
        return flightCreateRequestSO;
    }

    private void setFlightName(FlightSO flightSO, String flightName) {
        Matcher matcher = pattern.matcher(flightName);
        if (!matcher.matches()) {
            throw new RuntimeException("항공편명이 적절하지 않습니다");
        }
        flightSO.setFlightName(flightName);
    }

    private void setTargetName(FlightSO flightSO, String targetName) {
        flightSO.setTargetName(targetName);
    }


    private void setSourceName(FlightSO flightSO, String sourceName) {
        flightSO.setSourceName(sourceName);
    }

    private static void nullcheck(FlightCreateRequestDetail flightCreateRequestDetail) {
        if (StringUtils.isEmpty(flightCreateRequestDetail.getFlightName())) {
            throw new RuntimeException("항공편명이 없습니다.");
        }
        if (StringUtils.isEmpty(flightCreateRequestDetail.getSourceName())) {
            throw new RuntimeException("출발국가명이 없습니다.");
        }
        if (StringUtils.isEmpty(flightCreateRequestDetail.getTargetName())) {
            throw new RuntimeException("도착국가명이 없습니다.");
        }
    }

    public List<String> deleteRequestValidate(FlightDeleteRequest flightDeleteRequest) {
        if (CollectionUtils.isEmpty(flightDeleteRequest.getFlightIds())) {
            throw new RuntimeException("삭제할 항공편 아이디가 없습니다.");
        }
        for (String flightId : flightDeleteRequest.getFlightIds()) {
            if (StringUtils.isEmpty(flightId)) {
                throw new RuntimeException("삭제할 항공편 아이디가 없습니다.");
            }
        }
        List<String> flightIds = flightDeleteRequest.getFlightIds();

        List<FlightEntity> flightEntityList = flightDao.getListAllFlightByFlightIds(flightIds);
        if(flightEntityList.size() != flightIds.size()){
            throw new RuntimeException("적절하지 않은 항공편 아이디가 있습니다");
        }
        return flightIds;
    }
}
