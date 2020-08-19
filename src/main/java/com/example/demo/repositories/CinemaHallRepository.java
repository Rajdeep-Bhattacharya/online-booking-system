package com.example.demo.repositories;

import com.example.demo.domain.CinemaHallEntity;
import com.example.demo.models.CinemaHall;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CinemaHallRepository extends CrudRepository<CinemaHallEntity,Integer> {

    Optional<CinemaHallEntity> findByName(String name);
}
