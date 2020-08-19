package com.example.demo.models.response;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    boolean response;
    String error;
}
