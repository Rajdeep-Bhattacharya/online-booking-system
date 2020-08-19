package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "movie")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "city")
    private String city;

    @Column(name = "genre")
    private String genre;

}
