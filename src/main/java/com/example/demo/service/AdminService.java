package com.example.demo.service;

import com.example.demo.domain.*;

import com.example.demo.helper.AdminHelper;
import com.example.demo.models.*;
import com.example.demo.models.request.CreateCinemaRequest;
import com.example.demo.models.request.CreateMovieRequest;
import com.example.demo.models.request.CreateShowRequest;
import com.example.demo.models.response.CreateCinemaResponse;

import com.example.demo.models.response.CreateShowResponse;
import com.example.demo.repositories.*;
import com.example.demo.utils.DateTimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private CinemaRepository cinemaRepository;
    private CinemaHallRepository cinemaHallRepository;
    private CinemaHallSeatRepository cinemaHallSeatRepository;
    private AdminHelper adminHelper;
    private MovieRepository movieRepository;
    private ShowRepository showRepository;
    private ShowSeatBookingRepository showSeatBookingRepository;



    public AdminService(CinemaRepository cinemaRepository, CinemaHallRepository cinemaHallRepository, CinemaHallSeatRepository cinemaHallSeatRepository, AdminHelper adminHelper, MovieRepository movieRepository, ShowRepository showRepository,ShowSeatBookingRepository showSeatBookingRepository) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaHallRepository = cinemaHallRepository;
        this.cinemaHallSeatRepository = cinemaHallSeatRepository;
        this.adminHelper = adminHelper;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.showSeatBookingRepository = showSeatBookingRepository;
    }

    public CreateCinemaResponse createCinema(CreateCinemaRequest createCinemaRequest) {
        CinemaEntity cinemaEntity = CinemaEntity.builder()
                .name(createCinemaRequest.getName())
                .addressLine(createCinemaRequest.getLocation().getAddressLine())
                .city(createCinemaRequest.getLocation().getCity())
                .state(createCinemaRequest.getLocation().getState())
                .pin(createCinemaRequest.getLocation().getPin())
                .totalCinemaHall(createCinemaRequest.getCinemaHallsList().size())
                .build();
        CinemaEntity cinema = cinemaRepository.save(cinemaEntity);
        if (ObjectUtils.isEmpty(cinema)) {
            return CreateCinemaResponse.builder().response(false).error("failed to create entity cinema").build();
        }

        List<CinemaHallEntity> cinemaHallEntities = createCinemaRequest.getCinemaHallsList().stream()
                .filter(AdminService::validate)
                .map(k -> CinemaHallEntity.builder()
                        .name(k.getName())
                        .cinemaId(cinema.getId())
                        .totalSeats(k.getTotalSeats()).build())
                .collect(Collectors.toList());
        cinemaHallRepository.saveAll(cinemaHallEntities);
        // TODO : might have to iterate over iterable
        Map<String, Integer> nameToIdMap = cinemaHallEntities.stream().collect(Collectors.toMap(CinemaHallEntity::getName, CinemaHallEntity::getId));

        for (CinemaHall cinemaHall : createCinemaRequest.getCinemaHallsList()) {
            for (CinemaHallSeat seat : cinemaHall.getSeats()) {
                CinemaHallSeatEntity seatEntity = CinemaHallSeatEntity.builder().cinemaHallId(nameToIdMap.get(cinemaHall.getName()))
                        .seatCol(seat.getSeatCol())
                        .seatRow(seat.getSeatRow())
                        .price(seat.getPrice())
                        .build();
                cinemaHallSeatRepository.save(seatEntity);
            }
        }
        return CreateCinemaResponse.builder().error("success").response(true).build();
    }

    private static boolean validate(CinemaHall cinemaHall) {
        return true;
    }


    public boolean createMovie(CreateMovieRequest createMovieRequest) {
        MovieEntity req = null;
        try {
            LocalDateTime dateTime = DateTimeUtils.getLocalDateTimefromString(createMovieRequest.getReleaseDate(),DateTimeUtils.format);
            req = movieRepository.save(MovieEntity.builder()
                    .genre(createMovieRequest.getGenre())
                    .language(createMovieRequest.getLanguage())
                    .releaseDate(dateTime)
                    .title(createMovieRequest.getTitle())
                    .city(createMovieRequest.getCity())
                    .build());
        } catch (Exception e) {
            System.out.println("failed to parse date");
        }
        return !ObjectUtils.isEmpty(req);
    }


    public CreateShowResponse createShow(CreateShowRequest createShowRequest) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findByTitle(createShowRequest.getMovieTitle());
        if (!movieEntityOptional.isPresent())
            return CreateShowResponse.builder().error("movie does not exist please create the movie").response(false).build();
        Optional<CinemaHallEntity> cinemaHallEntityOptional = cinemaHallRepository.findByName(createShowRequest.getCinemaHallName());
        if (!cinemaHallEntityOptional.isPresent()) {
            return CreateShowResponse.builder().error("cinema hall does not exist please create the movie").response(false).build();
        }
        LocalDateTime created =  DateTimeUtils.getCurrentDateTime();
        LocalDateTime startTime = DateTimeUtils.getLocalDateTimefromString(createShowRequest.getStartTime(),DateTimeUtils.format);
        LocalDateTime endTime = DateTimeUtils.getLocalDateTimefromString(createShowRequest.getEndTime(),DateTimeUtils.format);
        if(startTime.isAfter(endTime)){
            return CreateShowResponse.builder().error("start time cannot be after end time").response(false).build();
        }
        if(showRepository.findOverlappingShows(startTime.toString(),endTime.toString(),createShowRequest.getCinemaHallName()) > 0){
            return CreateShowResponse.builder().error("show with overlapping start or end exists").response(false).build();
        }

        ShowsEntity showsEntity = showRepository.save(ShowsEntity.builder()
                .created(created)
                .endTime(endTime)
                .startTime(startTime)
                .cinemaHallName(cinemaHallEntityOptional.get().getName())
                .movieTitle(movieEntityOptional.get().getTitle()).build());
        if(ObjectUtils.isEmpty(showsEntity)){
            return CreateShowResponse.builder().response(false).error("failed to insert show").build();
        }
        else{
            List<CinemaHallSeatEntity> seats = cinemaHallEntityOptional.get().getSeats();
            List<ShowSeatBookingEntity> bulkInsert = new ArrayList<>();
            for(CinemaHallSeatEntity seatEntity : seats){
                ShowSeatBookingEntity showSeatBookingEntity = new ShowSeatBookingEntity();
                ShowSeatBookingIdentity identity = new ShowSeatBookingIdentity();
                identity.setSeatId(seatEntity.getId());
                identity.setShowId(showsEntity.getId());
                showSeatBookingEntity.setShowSeatBookingIdentity(identity);
                showSeatBookingEntity.setPrice(seatEntity.getPrice());
                showSeatBookingEntity.setStatus(true);
                bulkInsert.add(showSeatBookingEntity);
            }
            showSeatBookingRepository.saveAll(bulkInsert);
        }
        return CreateShowResponse.builder().response(true).build();
    }


    public List<Cinema> getCinemas() {
        Iterable<CinemaEntity> CinemaEntitiesIterable = cinemaRepository.findAll();
        Iterable<ShowsEntity> showsEntityIterable = showRepository.findAll();

        List<ShowsEntity> showsEntityList = new ArrayList<>();
        showsEntityIterable.forEach(showsEntityList::add);
        List<CinemaEntity> cinemaEntityList = new ArrayList<>();
        CinemaEntitiesIterable.forEach(cinemaEntityList::add);

        return adminHelper.getCinemasFromEntities(cinemaEntityList, showsEntityList);
    }

}
