package com.example.demo.dao;

import com.example.demo.domain.CinemaHallSeatEntity;
import com.example.demo.repositories.CinemaHallSeatRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class HallSeatDao {
    private CinemaHallSeatRepository hallSeatRepository;

    public HallSeatDao(CinemaHallSeatRepository hallSeatRepository){
        this.hallSeatRepository = hallSeatRepository;
    }

    public List<CinemaHallSeatEntity> findAllByIds(Set<Integer> ids){
        return hallSeatRepository.findAllByIdIn(ids);
    }


}
