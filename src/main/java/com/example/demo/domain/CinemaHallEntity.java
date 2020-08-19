package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "cinema_hall")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name ="cinema_id")
    private int cinemaId;

    @Column(name = "total_seats")
    private int totalSeats;

    @OneToMany(mappedBy = "cinemaHallId", fetch = FetchType.EAGER)
    List<CinemaHallSeatEntity> seats;

}
