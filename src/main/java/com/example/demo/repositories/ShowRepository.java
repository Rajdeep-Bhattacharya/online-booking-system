package com.example.demo.repositories;

import com.example.demo.domain.ShowsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends CrudRepository<ShowsEntity,Integer> {

    @Query(value = "select cinema_id from shows inner join cinema_hall on shows.cinema_hall_name = cinema_hall.name where shows.movie_title = :movieTitle and shows.start_time > :currTime", nativeQuery = true)
    List<Integer> findCinemaIdByMovieTitle(@Param("movieTitle") String movieTitle, @Param("currTime") String currTime);

    List<ShowsEntity> findByMovieTitleAndStartTimeAfter(String movieTitle,LocalDateTime currTime);

    @Query(value = "select count(*) from shows where ((shows.end_time BETWEEN :currStart and :currEnd) or (shows.start_time between :currStart and :currEnd) or (shows.start_time < :currStart and shows.end_time>:currEnd)) and shows.cinema_hall_name = :cinemaHallName", nativeQuery = true)
    int findOverlappingShows(@Param("currStart")String startTime,@Param("currEnd") String endTime,@Param("cinemaHallName") String movieHallName);



}
