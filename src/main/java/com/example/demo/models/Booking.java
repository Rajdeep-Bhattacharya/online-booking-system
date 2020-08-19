package com.example.demo.models;

import com.example.demo.enums.BookingStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Booking {
    private String bookingNumber;
    private int numberOfSeats;
    private Date createdOn;
    private BookingStatus status;

    private Show show;
    private List<ShowSeat> seats;

    public boolean cancel() {
        return false;
    }

    public boolean assignSeats(List<ShowSeat> seats) {
        return false;
    }
}
