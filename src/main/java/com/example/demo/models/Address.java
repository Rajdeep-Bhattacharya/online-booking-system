package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @JsonProperty("address_line")
    private String addressLine;

    private String city;

    private String state;

    private String pin;

}
