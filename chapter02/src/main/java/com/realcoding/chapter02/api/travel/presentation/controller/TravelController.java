package com.realcoding.chapter02.api.travel.presentation.controller;

import com.realcoding.chapter02.api.common.code.ApplicationConstants;
import com.realcoding.chapter02.api.travel.presentation.converter.TravelControllerConverter;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequest;
import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelDeleteRequest;
import com.realcoding.chapter02.api.travel.presentation.dto.out.TravelDTO;
import com.realcoding.chapter02.api.travel.presentation.validator.TravelValidator;
import com.realcoding.chapter02.api.travel.service.logic.TravelService;
import com.realcoding.chapter02.api.travel.service.so.TravelCreateRequestSO;
import com.realcoding.chapter02.api.travel.service.so.TravelSO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApplicationConstants.URL_PREFIX_TRAVEL_CONSOLE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class TravelController {

    private final TravelValidator travelValidator;
    private final TravelService travelService;
    private final TravelControllerConverter travelControllerConverter;

    @GetMapping("/list/all")
    public ResponseEntity<List<TravelDTO>> getListAllTravel() throws Exception {
        List<TravelSO> travelSOList = travelService.getListAllTravel();
        List<TravelDTO> travelDTOList = travelControllerConverter.toTravelDTOList(travelSOList);
        return ResponseEntity.ok(travelDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<List<TravelDTO>> createTravel(@RequestBody TravelCreateRequest travelCreateRequest) throws Exception {
        TravelCreateRequestSO travelCreateRequestSO = travelValidator.createRequestValidate(travelCreateRequest);
        List<TravelSO> travelSOList = travelService.createTravelList(travelCreateRequestSO);
        List<TravelDTO> flightDTOList = travelControllerConverter.toTravelDTOList(travelSOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightDTOList);
    }

    @GetMapping("/detail/{travelId}")
    public ResponseEntity<TravelDTO> getTravelDetail(@PathVariable String travelId) {
        TravelSO travelSO = travelService.getTravelDetailByTravelId(travelId);
        TravelDTO flightDTO = travelControllerConverter.toTravelDTO(travelSO);
        return ResponseEntity.ok(flightDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFlights(@RequestBody TravelDeleteRequest travelDeleteRequest) {
        List<String> travelIds = travelValidator.deleteRequestValidate(travelDeleteRequest);
        int ret = travelService.deleteTravelList(travelIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
