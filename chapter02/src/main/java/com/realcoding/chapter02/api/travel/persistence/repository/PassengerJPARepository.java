package com.realcoding.chapter02.api.travel.persistence.repository;

import com.realcoding.chapter02.api.passenger.persistence.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface PassengerJPARepository extends JpaRepository<PassengerEntity, String> {

    PassengerEntity findByPassengerId(String passengerId);
}
