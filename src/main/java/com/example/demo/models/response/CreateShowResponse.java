package com.example.demo.models.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateShowResponse extends BaseResponse {

    @Builder
    public CreateShowResponse(boolean response, String error){
        super(response, error);
    }
}
