package com.realcoding.chapter02.api.flight.presentation.converter;

import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightControllerConverter {
    List<FlightDTO> toFlightDTOList(List<FlightSO> flightSOList);

    FlightDTO toFlightDTO(FlightSO flightSO);
}
