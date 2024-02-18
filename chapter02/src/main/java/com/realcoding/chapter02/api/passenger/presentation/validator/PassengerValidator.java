package com.realcoding.chapter02.api.passenger.presentation.validator;

import com.realcoding.chapter02.api.common.config.PassengerConfig;
import com.realcoding.chapter02.api.common.exception.CustomException;
import com.realcoding.chapter02.api.common.exception.ErrorCode;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.passenger.persistence.dao.PassengerDao;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequestDetail;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerDeleteRequest;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassengerValidator {

    // 주민등록번호는 앞숫자 6개, 하이픈, 뒷숫자 7개로 구성되어 있으며 뒷숫자의 가장 앞은 1, 2, 3, 4로만 구성된다.
    private static final String VALID_PASSENGER_RRN_REGEX = "^\\d{6}-[1234]\\d{6}$";
    private static final Pattern pattern = Pattern.compile(VALID_PASSENGER_RRN_REGEX);

    private final PassengerDao passengerDao;
    private final PassengerConfig passengerConfig;

    public void validateUpdatePassengerType(String passengerId, String type) {
        nullCheck(passengerId, type);
        PassengerEntity passengerEntity = passengerDao.getPassengerById(passengerId);
        if (StringUtils.isEmpty(passengerEntity.getPassengerId())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "존재하지 않는 승객입니다.");
        }
        Set<String> passengerTypeSet = passengerConfig.getTypes().stream().collect(Collectors.toSet());
        if (!passengerTypeSet.contains(type)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "존재하지 않는 타입입니다.");
        }
    }

    private void nullCheck(String passengerId, String type) {
        if (StringUtils.isEmpty(passengerId)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "승객아이디가 적절하지 않습니다.");
        }
        if (StringUtils.isEmpty(type)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "타입이 적절하지 않습니다.");
        }
    }

    public List<String> deleteRequestValidate(PassengerDeleteRequest deleteRequest) {
        if (CollectionUtils.isEmpty(deleteRequest.getPassengerIds())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "삭제할 승객 아이디가 없습니다.");
        }
        for (String passengerId : deleteRequest.getPassengerIds()) {
            if (StringUtils.isEmpty(passengerId)) {
                throw new CustomException(ErrorCode.BAD_REQUEST, "삭제할 승객 아이디가 없습니다.");
            }
        }
        List<String> passengerIds = deleteRequest.getPassengerIds();

        List<PassengerEntity> passengerEntityList = passengerDao.getListAllPassengersByPassengerIds(passengerIds);
        if (passengerEntityList.size() != passengerIds.size()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "적절하지 않은 승객 아이디가 있습니다");
        }
        return passengerIds;
    }

    public PassengerCreateRequestSO createRequestValidate(PassengerCreateRequest createRequest) {
        PassengerCreateRequestSO passengerCreateRequestSO = new PassengerCreateRequestSO();
        List<PassengerSO> passengerSOList = new ArrayList<>();

        for (PassengerCreateRequestDetail passengerCreateRequestDetail : createRequest.getPassengerDetailList()) {
            PassengerSO passengerSO = new PassengerSO();
            nullCheck(passengerCreateRequestDetail);
            setPassengerRRN(passengerSO, passengerCreateRequestDetail.getPassengerRRN());
            setName(passengerSO, passengerCreateRequestDetail.getName());
            setType(passengerSO, passengerCreateRequestDetail.getType());
            passengerSOList.add(passengerSO);
        }
        passengerCreateRequestSO.setPassengerSOList(passengerSOList);
        return passengerCreateRequestSO;
    }

    private void nullCheck(PassengerCreateRequestDetail passengerCreateRequestDetail) {
        if (StringUtils.isEmpty(passengerCreateRequestDetail.getPassengerRRN())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "주민등록번호가 적절하지 않습니다.");
        }
        if (StringUtils.isEmpty(passengerCreateRequestDetail.getName())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "승객 이름이 적절하지 않습니다.");
        }
        if (StringUtils.isEmpty(passengerCreateRequestDetail.getType())) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "승객 유형이 적절하지 않습니다.");
        }
    }

    private void setPassengerRRN(PassengerSO passengerSO, String passengerRRN) {
        Matcher matcher = pattern.matcher(passengerRRN);
        if (!matcher.matches()) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "주민등록번호가 적절하지 않습니다");
        }
        passengerSO.setPassengerRRN(passengerRRN);
    }

    private void setName(PassengerSO passengerSO, String name) {
        passengerSO.setName(name);
    }

    private void setType(PassengerSO passengerSO, String type) {
        Set<String> passengerTypeSet = passengerConfig.getTypes().stream().collect(Collectors.toSet());
        if (!passengerTypeSet.contains(type)) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "존재하지 않는 타입입니다.");
        }
        passengerSO.setType(type);
    }
}
