package com.example.demo.repositories;

import com.example.demo.domain.CinemaEntity;
import com.example.demo.models.Cinema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface CinemaRepository extends CrudRepository<CinemaEntity,Integer> {
    @Query(value = "select distinct city from cinema", nativeQuery = true)
    List<String> findAllCities();
}
