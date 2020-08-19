package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "cinema_hall_seat")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallSeatEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "seat_row")
    private int seatRow;

    @Column(name = "seat_col")
    private int seatCol;

    @Column(name = "cinema_hall_id")
    private int cinemaHallId;

    private int price;

}
