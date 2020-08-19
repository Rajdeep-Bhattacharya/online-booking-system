package com.example.demo.models.request;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMovieRequest {

    private String title;

    private String language;

    @JsonProperty("release_date")
    private String releaseDate;

    private String city;

    private String genre;
}
