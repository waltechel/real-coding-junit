package com.realcoding.chapter02.api.travel.presentation.converter;

import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import com.realcoding.chapter02.api.travel.presentation.dto.out.TravelDTO;
import com.realcoding.chapter02.api.travel.service.so.TravelSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TravelControllerConverter {
    List<TravelDTO> toTravelDTOList(List<TravelSO> travelSOList);

    TravelDTO toTravelDTO(TravelSO travelSO);
}
