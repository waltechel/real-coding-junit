package com.realcoding.chapter02.api.passenger.presentation.validator;

import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerDeleteRequest;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PassengerValidator {
    public void validateUpdatePassengerType(String passengerId, String type) {
    }

    public List<String> deleteRequestValidate(PassengerDeleteRequest deleteRequest) {
        return null;
    }

    public PassengerCreateRequestSO createRequestValidate(PassengerCreateRequest createRequest) {
        return null;
    }
}
