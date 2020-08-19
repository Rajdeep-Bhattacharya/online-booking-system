package com.example.demo.models.response;

import com.example.demo.domain.CinemaEntity;
import lombok.Data;

import java.util.List;

@Data
public class GetCinemaResponse {
    List<CinemaEntity> cinemas;
}
