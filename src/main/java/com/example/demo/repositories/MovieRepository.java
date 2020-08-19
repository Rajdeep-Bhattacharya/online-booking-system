package com.example.demo.repositories;

import com.example.demo.domain.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<MovieEntity,Integer> {

    Optional<MovieEntity> findByTitle(String title);

    @Query(value = "select distinct title from movie where city = :city", nativeQuery = true)
    List<String> findMovieTitleByCity(@Param("city") String city);
}
