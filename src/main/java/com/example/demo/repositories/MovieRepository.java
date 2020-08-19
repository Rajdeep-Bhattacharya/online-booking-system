package com.example.demo.repositories;

import com.example.demo.domain.MovieEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<MovieEntity,Integer> {

    Optional<MovieEntity> findByTitle(String title);
}
