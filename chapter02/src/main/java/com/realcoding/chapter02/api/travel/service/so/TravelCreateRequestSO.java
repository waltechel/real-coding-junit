package com.realcoding.chapter02.api.travel.service.so;

import com.realcoding.chapter02.api.travel.presentation.dto.in.TravelCreateRequestDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TravelCreateRequestSO {

    private List<TravelCreateRequestSODetail> travelDetailSOList;

}
