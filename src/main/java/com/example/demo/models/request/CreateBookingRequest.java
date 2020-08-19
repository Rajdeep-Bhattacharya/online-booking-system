package com.example.demo.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookingRequest {
    @JsonProperty("show_id")
    int showId;

    @JsonProperty("seat_ids")
    List<Integer> seatIds;
}
