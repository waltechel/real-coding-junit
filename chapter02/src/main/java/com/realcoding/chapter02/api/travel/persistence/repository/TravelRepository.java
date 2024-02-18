package com.realcoding.chapter02.api.travel.persistence.repository;

import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<TravelEntity, String> {
}
