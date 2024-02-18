package com.realcoding.chapter02.api.passenger.service.converter;

import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassengerServiceConverter {
    List<PassengerSO> toPassengerSOList(List<PassengerEntity> passengerEntityList);

    PassengerSO toPassengerSO(PassengerEntity passengerEntity);
}
