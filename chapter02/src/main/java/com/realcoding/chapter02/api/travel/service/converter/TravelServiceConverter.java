package com.realcoding.chapter02.api.travel.service.converter;

import com.realcoding.chapter02.api.flight.service.converter.FlightServiceConverter;
import com.realcoding.chapter02.api.passenger.service.converter.PassengerServiceConverter;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.service.so.TravelSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {FlightServiceConverter.class, PassengerServiceConverter.class})
public interface TravelServiceConverter {
    List<TravelSO> toTravelSOList(List<TravelEntity> travelEntityList);

    TravelSO toTravelSO(TravelEntity travelEntity);
}
