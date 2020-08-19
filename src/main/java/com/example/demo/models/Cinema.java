package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cinema {
    private String name;
    private int totalCinemaHalls;
    private Address location;

    private List<CinemaHall> halls;
}
