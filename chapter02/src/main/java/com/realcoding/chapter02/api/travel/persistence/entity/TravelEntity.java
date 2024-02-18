package com.realcoding.chapter02.api.travel.persistence.entity;

import com.realcoding.chapter02.api.common.code.BaseEntity;
import com.realcoding.chapter02.api.flight.persistence.entity.FlightEntity;
import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelEntity extends BaseEntity {

    @Id
    @Column(name = "travel_id")
    private String travelId;

    @Column(name = "status")
    private String status;

    // 일대일 매핑을 위한 Flight 엔티티 참조
    @OneToOne
    @JoinColumn(name = "flight_id") // rc_flight 테이블의 flight_id 컬럼과 연결
    private FlightEntity flight;

    // 일대일 매핑을 위한 Passenger 엔티티 참조
    @OneToOne
    @JoinColumn(name = "passenger_id") // rc_passenger 테이블의 passenger_id 컬럼과 연결
    private PassengerEntity passenger;

}
