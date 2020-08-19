package com.example.demo.models.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateShowRequest {

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty( "end_time")
    private String endTime;

    @JsonProperty("movie_title")
    private String movieTitle;

    @JsonProperty("cinema_hall_name")
    private String cinemaHallName;

}
