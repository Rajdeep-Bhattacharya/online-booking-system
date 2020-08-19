package com.example.demo.models.request;

import com.example.demo.models.Address;
import com.example.demo.models.CinemaHall;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCinemaRequest {
    private String name;
    private Address location;

    @JsonProperty("cinema_halls_data")
    private List<CinemaHall> cinemaHallsList;
}
