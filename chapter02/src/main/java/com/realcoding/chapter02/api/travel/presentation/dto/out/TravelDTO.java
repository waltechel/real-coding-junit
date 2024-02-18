package com.realcoding.chapter02.api.travel.presentation.dto.out;

import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.flight.presentation.dto.out.FlightDTO;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import com.realcoding.chapter02.api.passenger.presentation.dto.out.PassengerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TravelDTO {

    private String travelId;

    private String status;

    private FlightDTO flight;

    private PassengerDTO passenger;

    private LocalDateTime createdDt;

    private LocalDateTime modifiedDt;
}
