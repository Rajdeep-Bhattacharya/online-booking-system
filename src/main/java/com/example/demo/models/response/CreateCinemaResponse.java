package com.example.demo.models.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateCinemaResponse extends BaseResponse {
    @Builder
   public CreateCinemaResponse(String error,Boolean response){
       super(response,error);
   }
}
