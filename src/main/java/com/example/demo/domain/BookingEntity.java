package com.example.demo.domain;


import com.example.demo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "booking")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "no_of_seats")
    private int noOfSeats;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "status")
    private BookingStatus status;
}
