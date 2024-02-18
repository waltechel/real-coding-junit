package com.realcoding.chapter02.api.travel.persistence.dao.impl;

import com.realcoding.chapter02.api.travel.persistence.dao.TravelDao;
import com.realcoding.chapter02.api.travel.persistence.entity.TravelEntity;
import com.realcoding.chapter02.api.travel.persistence.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TravelDaoImpl implements TravelDao {

    private final TravelRepository travelRepository;

    @Override
    public List<TravelEntity> saveAllTravelList(List<TravelEntity> travelEntityList) {
        List<TravelEntity> ret = new ArrayList<>();
        try {
            ret = travelRepository.saveAll(travelEntityList);
        } catch (RuntimeException re) {
            log.error("TravelDaoImpl > saveAllTravelList has error! ", re);
        }
        return ret;
    }
}
