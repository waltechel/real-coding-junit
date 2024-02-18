package com.realcoding.chapter02.api.flight.presentation.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FlightDTO {

    private String flightId;
    private String flightName;
    private String sourceName;
    private String targetName;
    private String type;
}
