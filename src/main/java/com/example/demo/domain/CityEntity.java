package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "city")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @Column(name = "pin")
    private String pin;
}
