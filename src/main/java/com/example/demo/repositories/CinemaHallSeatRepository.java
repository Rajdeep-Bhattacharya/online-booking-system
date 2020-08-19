package com.example.demo.repositories;

import com.example.demo.domain.CinemaHallSeatEntity;
import com.example.demo.models.CinemaHallSeat;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;


public interface CinemaHallSeatRepository extends CrudRepository<CinemaHallSeatEntity,Integer> {

    List<CinemaHallSeatEntity> findAllByIdIn(Set<Integer> ids);
}
