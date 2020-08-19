package com.example.demo.models.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetMoviesFromCityResponse extends BaseResponse {

    List<String> movies;
    @Builder
    public GetMoviesFromCityResponse(String error,Boolean response){
        super(response,error);
        this.movies = new ArrayList<>();
    }

}
