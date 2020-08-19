package com.example.demo.repositories;

import com.example.demo.domain.ShowsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends CrudRepository<ShowsEntity,Integer> {

    @Query(value = "select cinema_id from shows inner join cinema_hall on shows.cinema_hall_name = cinema_hall.name where shows.movie_title = :movieTitle", nativeQuery = true)
    List<Integer> findCinemaIdByMovieTitle(@Param("movieTitle") String movieTitle);

    List<ShowsEntity> findByMovieTitle(String movieTitle);

}
