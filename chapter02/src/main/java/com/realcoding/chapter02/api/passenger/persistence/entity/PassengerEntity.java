package com.realcoding.chapter02.api.passenger.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // JPA 엔티티임을 나타냄
public class PassengerEntity {

    @Id // 기본 키임을 나타냄
    @Column(name = "passenger_id")
    private String passengerId;

    @Column(name = "passenger_rrn")
    private String passengerRRN;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_dt")
    private LocalDateTime modifiedDt;

    @Column(name = "modified_by")
    private String modifiedBy;
}