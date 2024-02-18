package com.realcoding.chapter02.api.travel.persistence.repository;

import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelRepository extends JpaRepository<TravelEntity, String> {
    
    // findAllTravelList() 메서드에 fetch join 적용
    @Query("SELECT distinct t FROM rc_travel t " +
            "JOIN FETCH t.flight f " +
            "JOIN FETCH t.passenger p")
    List<TravelEntity> findAllTravelList();

    // findByTravelId() 메서드에 fetch join 적용
    @Query("SELECT distinct t FROM rc_travel t " +
            "JOIN FETCH t.flight f " +
            "JOIN FETCH t.passenger p " +
            "WHERE t.travelId = :travelId")
    TravelEntity findByTravelId(@Param("travelId") String travelId);

    // findAllByTravelIds() 메소드에 fetch join 적용
    @Query("SELECT distinct t FROM rc_travel t " +
            "JOIN FETCH t.flight f " +
            "JOIN FETCH t.passenger p " +
            "WHERE t.travelId IN :travelIds")
    List<TravelEntity> findAllByTravelIds(@Param("travelIds") List<String> travelIds);
}
