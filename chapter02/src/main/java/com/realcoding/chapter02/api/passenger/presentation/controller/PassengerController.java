package com.realcoding.chapter02.api.passenger.presentation.controller;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.passenger.presentation.converter.PassengerControllerConverter;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerCreateRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerDeleteRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.in.PassengerUpdateTypeRequest;
import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import com.realcoding.chapter02.api.passenger.presentation.validator.PassengerValidator;
import com.realcoding.chapter02.api.passenger.service.logic.PassengerService;
import com.realcoding.chapter02.api.passenger.service.so.PassengerCreateRequestSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApplicationConstants.URL_PREFIX_PASSENGER_CONSOLE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerValidator passengerValidator;
    private final PassengerControllerConverter passengerControllerConverter;

    @GetMapping("/list/all")
    public ResponseEntity<List<PassengerDTO>> getListAllPassengers() {
        List<PassengerSO> passengerSOList = passengerService.getListAllPassengerList();
        List<PassengerDTO> passengerDTOList = passengerControllerConverter.toPassengerDTOList(passengerSOList);
        return ResponseEntity.ok(passengerDTOList);
    }


    @GetMapping("/detail/{passengerId}")
    public ResponseEntity<PassengerDTO> getPassengerDetail(@PathVariable String passengerId) {
        PassengerSO passengerSO = passengerService.getPassengerDetailByPassengerId(passengerId);
        PassengerDTO passengerDTO = passengerControllerConverter.toPassengerDTO(passengerSO);
        return ResponseEntity.ok(passengerDTO);
    }


    @PutMapping("/update/{passengerId}")
    public ResponseEntity<PassengerSO> updatePassengerType(@PathVariable String passengerId,
                                                           @RequestBody PassengerUpdateTypeRequest request) {
        passengerValidator.validateUpdatePassengerType(passengerId, request.getType());
        PassengerSO passengerSO = passengerService.updatePassengerType(passengerId, request.getType());
        return ResponseEntity.ok(passengerSO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePassenger(@RequestBody PassengerDeleteRequest deleteRequest) {
        List<String> passengerIds = passengerValidator.deleteRequestValidate(deleteRequest);
        passengerService.deletePassenger(passengerIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/create")
    public ResponseEntity<List<PassengerDTO>> createPassenger(@RequestBody PassengerCreateRequest createRequest) {
        PassengerCreateRequestSO passengerCreateRequestSO = passengerValidator.createRequestValidate(createRequest);
        List<PassengerSO> passengerSOLIst = passengerService.createPassengerList(passengerCreateRequestSO);
        List<PassengerDTO> passengerDTOList = passengerControllerConverter.toPassengerDTOList(passengerSOLIst);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerDTOList);
    }
}
