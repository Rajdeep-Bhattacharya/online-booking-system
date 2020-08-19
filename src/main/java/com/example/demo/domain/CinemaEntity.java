package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "cinema")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_cinema_hall")
    private int totalCinemaHall;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "pin")
    private String pin;

    @OneToMany(mappedBy = "cinemaId", fetch = FetchType.EAGER)
    List<CinemaHallEntity> halls;

}
