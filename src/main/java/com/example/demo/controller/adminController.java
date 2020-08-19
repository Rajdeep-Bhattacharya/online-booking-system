package com.example.demo.controller;


import com.example.demo.models.Cinema;
import com.example.demo.models.request.CreateCinemaRequest;
import com.example.demo.models.request.CreateMovieRequest;
import com.example.demo.models.request.CreateShowRequest;
import com.example.demo.models.response.CreateCinemaResponse;
import com.example.demo.models.response.CreateMovieResponse;
import com.example.demo.models.response.CreateShowResponse;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/api")
public class adminController {

    private AdminService adminService;
    public adminController(AdminService adminService){
        this.adminService = adminService;
    }


    @RequestMapping(value = "/cinema/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateCinemaResponse> createCinema(@RequestBody CreateCinemaRequest createCinemaRequest) {
        if(!ObjectUtils.isEmpty(createCinemaRequest)){
            return new ResponseEntity<>(adminService.createCinema(createCinemaRequest), HttpStatus.OK);
        }
        return new ResponseEntity<>(CreateCinemaResponse.builder().response(false).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/show/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateShowResponse> createShow(@RequestBody CreateShowRequest createShowRequest) {
        if(!ObjectUtils.isEmpty(createShowRequest)) {
            return new ResponseEntity<>(adminService.createShow(createShowRequest),HttpStatus.OK);
        }
        return new ResponseEntity<>(CreateShowResponse.builder().response(false).build(), HttpStatus.OK);
    }


    @RequestMapping(value = "/movies/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateMovieResponse> createMovie(@RequestBody CreateMovieRequest createMovieRequest){
        if(!ObjectUtils.isEmpty(createMovieRequest)){
            return new ResponseEntity<>(CreateMovieResponse.builder().response(adminService.createMovie(createMovieRequest)).build(),HttpStatus.OK);
        }
        return new ResponseEntity<>(CreateMovieResponse.builder().response(false).error("failed to create movie").build(),HttpStatus.OK);
    }


    @RequestMapping(value = "/cinemas/fetch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> getCinemas() {
        return new ResponseEntity<>(adminService.getCinemas(),HttpStatus.OK);
    }




}
