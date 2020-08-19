package com.example.demo.domain;


import com.example.demo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "show_seat_booking_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatBookingEntity implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;*/

    @EmbeddedId
    ShowSeatBookingIdentity showSeatBookingIdentity;

    @Column(name = "booking_id")
    private int bookingId;


    @Column(columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "price")
    private int price;

    public boolean getStatus() {
        return status;
    }
}
