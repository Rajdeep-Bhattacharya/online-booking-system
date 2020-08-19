package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "shows")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_on")
    private LocalDateTime created;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name="movie_title")
    private String movieTitle;

    @Column(name="cinema_hall_name")
    private String cinemaHallName;

}
