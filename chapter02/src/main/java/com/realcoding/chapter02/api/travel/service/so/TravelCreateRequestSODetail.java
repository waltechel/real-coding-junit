package com.realcoding.chapter02.api.travel.service.so;

import com.realcoding.chapter02.api.flight.service.so.FlightSO;
import com.realcoding.chapter02.api.passenger.service.so.PassengerSO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TravelCreateRequestSODetail {

    private PassengerSO passengerSO;
    private FlightSO flightSO;

}
