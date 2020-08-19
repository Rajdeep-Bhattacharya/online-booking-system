package com.example.demo.models;

import lombok.Data;
import java.util.List;

@Data
public class Movie {


    private String title;
    private String language;
    private String releaseDate;
    private String country;
    private String genre;
    private List<Show> shows;

}
