package com.realcoding.chapter02.api.travel.service.so;

import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TravelSO {
    private String travelId;

    private String status;

    private FlightSO flight;

    private PassengerSO passenger;

    private LocalDateTime createdDt;

    private LocalDateTime modifiedDt;
}
