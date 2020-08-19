package com.example.demo.controller;


import com.example.demo.models.Cinema;
import com.example.demo.models.CinemaHallSeat;
import com.example.demo.models.request.CreateBookingRequest;
import com.example.demo.models.response.BookingResponse;
import com.example.demo.models.response.GetAvailableSeatsResponse;
import com.example.demo.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/api")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    @RequestMapping(value = "/cinemas/cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCitiesWithCinemas() {
        return new ResponseEntity<>(cinemaService.getCities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cinema/movie/shows",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> getCinemasWithAShowOfAGivenMovie(@RequestParam(value = "movie_title",required = true) String movieTitle){
        return new ResponseEntity<>(cinemaService.getCinemasWithAShowOfAGivenMovie(movieTitle),HttpStatus.OK);
    }

    @RequestMapping(value = "/show/seat/fetch", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAvailableSeatsResponse> getAvailableSeatsFromShowId(@RequestParam(value = "show_id",required = true) String showId){
        return new ResponseEntity<>(cinemaService.getAvailableSeats(showId),HttpStatus.OK);
    }
    @RequestMapping(value = "/cinema/show/book", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingResponse> bookShowWithSeatIds(@RequestBody CreateBookingRequest bookingRequest){
        try {
            return new ResponseEntity<>(cinemaService.bookSeats(bookingRequest), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new BookingResponse(false,e.getMessage()),HttpStatus.OK);
        }

    }
}