package com.realcoding.chapter02.api.passenger.presentation.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PassengerDTO {
    private String passengerId;
    private String passengerRRN;
    private String name;
    private String status;
    private String type;
    private LocalDateTime createdDt;
    private String createdBy;
    private LocalDateTime modifiedDt;
    private String modifiedBy;
}
