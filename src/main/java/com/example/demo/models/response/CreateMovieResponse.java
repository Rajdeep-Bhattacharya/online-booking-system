package com.example.demo.models.response;


import lombok.*;


@Getter
public class CreateMovieResponse extends BaseResponse{
    @Builder
    public CreateMovieResponse(String error,Boolean response){
        super(response,error);
    }
}
