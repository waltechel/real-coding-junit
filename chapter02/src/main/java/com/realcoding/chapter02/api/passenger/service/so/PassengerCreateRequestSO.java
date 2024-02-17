package com.realcoding.chapter02.api.passenger.service.so;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PassengerCreateRequestSO {

    private List<PassengerSO> passengerSOList;
}
