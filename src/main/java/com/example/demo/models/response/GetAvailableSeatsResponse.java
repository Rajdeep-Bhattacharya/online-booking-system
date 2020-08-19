package com.example.demo.models.response;

import com.example.demo.models.CinemaHallSeat;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetAvailableSeatsResponse extends BaseResponse {

    List<CinemaHallSeat> available;
    @Builder
    public GetAvailableSeatsResponse(String error,Boolean response){
        super(response,error);
        this.available = new ArrayList<>();
    }
}
