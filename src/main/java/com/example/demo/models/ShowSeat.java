package com.example.demo.models;

import lombok.Data;

@Data
public class ShowSeat extends CinemaHallSeat{
    private int showSeatId;
    private boolean isReserved;
    private int price;
}
