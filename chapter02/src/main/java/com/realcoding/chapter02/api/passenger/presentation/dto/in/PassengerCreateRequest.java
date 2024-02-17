package com.realcoding.chapter02.api.passenger.presentation.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PassengerCreateRequest {

    private List<PassengerCreateRequestDetail> passengerDetailList;
}
