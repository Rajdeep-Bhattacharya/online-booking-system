package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private int id;
    private String created;
    private String startTime;
    private String endTime;
    private String cinemaHallName;
    private String movieTitle;
}
