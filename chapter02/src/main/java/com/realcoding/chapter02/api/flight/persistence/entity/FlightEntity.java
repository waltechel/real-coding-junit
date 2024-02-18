package com.realcoding.chapter02.api.flight.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FlightEntity {

    @Id
    @Column(name = "flight_id")
    private String flightId;
    @Column(name = "flight_name")
    private String flightName;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "target_name")
    private String targetName;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "created_dt")
    private LocalDateTime createdDt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_dt")
    private LocalDateTime modifiedDt;

    @Column(name = "modified_by")
    private String modifiedBy;
}
