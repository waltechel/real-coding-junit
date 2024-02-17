package com.realcoding.chapter02.api.flight.service.converter;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightServiceConverter {
    List<FlightSO> toFlightSOList(List<FlightEntity> flightEntityList);

    FlightSO toFlightSO(FlightEntity flightEntity);
}
