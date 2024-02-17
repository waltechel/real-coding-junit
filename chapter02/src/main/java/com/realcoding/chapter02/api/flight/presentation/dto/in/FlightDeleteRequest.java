package com.realcoding.chapter02.api.flight.presentation.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDeleteRequest {

    private List<String> flightIds;
}
