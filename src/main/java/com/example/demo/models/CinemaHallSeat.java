package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CinemaHallSeat {
    @JsonProperty("id")
    private int seatId;

    @JsonProperty("row")
    private int seatRow;

    @JsonProperty("col")
    private int seatCol;

    private int price;

}
