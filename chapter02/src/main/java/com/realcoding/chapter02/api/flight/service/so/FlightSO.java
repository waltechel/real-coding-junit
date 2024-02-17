package com.realcoding.chapter02.api.flight.service.so;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FlightSO {

    private String flightId;
    private String flightName;
    private String sourceName;
    private String targetName;
    private String status;
    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;
}
