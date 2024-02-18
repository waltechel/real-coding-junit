package com.realcoding.chapter02.api.flight.presentation.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FlightCreateRequestDetail {

    private String flightName;
    private String sourceName;
    private String targetName;
    private String type;

}
