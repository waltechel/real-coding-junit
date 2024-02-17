package com.realcoding.chapter02.api.passenger.presentation.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PassengerCreateRequestDetail {
    private String passengerRRN;
    private String name;
    private String type;
}
