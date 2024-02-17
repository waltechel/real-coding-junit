package com.realcoding.chapter02.api.passenger.presentation.converter;

import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassengerControllerConverter {
    List<PassengerDTO> toPassengerDTOList(List<PassengerSO> passengerSOList);

    PassengerDTO toPassengerDTO(PassengerSO passengerSO);
}
