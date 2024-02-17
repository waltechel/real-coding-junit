package com.realcoding.chapter02.api.flight.presentation.controller;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.flight.presentation.converter.FlightControllerConverter;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightDeleteRequest;
import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.flight.presentation.validator.FlightValidator;
import com.realcoding.chapter02.api.flight.service.logic.FlightService;
import com.realcoding.chapter02.api.flight.service.so.FlightCreateRequestSO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApplicationConstants.URL_PREFIX_FLIGHT_CONSOLE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class FlightController {

    private final FlightValidator flightValidator;
    private final FlightService flightService;
    private final FlightControllerConverter flightControllerConverter;

    @GetMapping("/list/all")
    public ResponseEntity<List<FlightDTO>> getListAllFlight() throws Exception {
        List<FlightSO> flightSOList = flightService.getListAllFlight();
        List<FlightDTO> flightDTOList = flightControllerConverter.toFlightDTOList(flightSOList);
        return ResponseEntity.ok(flightDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<List<FlightDTO>> createFlight(@RequestBody FlightCreateRequest flightCreateRequest) throws Exception {
        FlightCreateRequestSO flightCreateRequestSO = flightValidator.createRequestValidate(flightCreateRequest);
        List<FlightSO> flightSOList = flightService.createFlightList(flightCreateRequestSO);
        List<FlightDTO> flightDTOList = flightControllerConverter.toFlightDTOList(flightSOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightDTOList);
    }

    @GetMapping("/detail/{flightId}")
    public ResponseEntity<FlightDTO> getFlightDetail(@PathVariable String flightId) {
        FlightSO flightSO = flightService.getFlightDetailByFlightId(flightId);
        FlightDTO flightDTO = flightControllerConverter.toFlightDTO(flightSO);
        return ResponseEntity.ok(flightDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFlights(@RequestBody FlightDeleteRequest flightDeleteRequest) {
        List<String> flightIds = flightValidator.deleteRequestValidate(flightDeleteRequest);
        int ret = flightService.deleteFlightList(flightIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
