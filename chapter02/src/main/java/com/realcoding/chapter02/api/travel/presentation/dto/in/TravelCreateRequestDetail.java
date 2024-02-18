package com.realcoding.chapter02.api.travel.presentation.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TravelCreateRequestDetail {

    private String passengerId;
    private String flightID;

}
