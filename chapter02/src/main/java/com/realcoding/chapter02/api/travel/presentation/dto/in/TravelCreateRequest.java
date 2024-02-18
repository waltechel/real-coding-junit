package com.realcoding.chapter02.api.travel.presentation.dto.in;

import com.realcoding.chapter02.api.flight.presentation.dto.in.FlightCreateRequestDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TravelCreateRequest {

    private List<TravelCreateRequestDetail> travelDetailList;
}
