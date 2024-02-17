package com.realcoding.chapter02.api.flight.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightEntity {

    private String flightId;
    private String flightName;
    private String sourceName;
    private String targetName;
    private String status;
    private LocalDateTime createdDt;
    private String createdBy;
    private LocalDateTime modifiedDt;
    private String modifiedBy;
}
