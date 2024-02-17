package com.realcoding.chapter02.api.flight.service.so;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightCreateRequestSO {

    private List<FlightSO> flightSOList;
}
