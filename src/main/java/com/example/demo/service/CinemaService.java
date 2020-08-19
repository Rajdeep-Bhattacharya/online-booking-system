package com.example.demo.service;


import com.example.demo.dao.HallSeatDao;
import com.example.demo.dao.ShowSeatBookingDao;
import com.example.demo.domain.BookingEntity;
import com.example.demo.domain.CinemaEntity;
import com.example.demo.domain.ShowSeatBookingEntity;
import com.example.demo.domain.ShowsEntity;
import com.example.demo.enums.BookingStatus;
import com.example.demo.helper.AdminHelper;
import com.example.demo.models.Cinema;
import com.example.demo.models.CinemaHallSeat;
import com.example.demo.models.request.CreateBookingRequest;
import com.example.demo.models.response.BookingResponse;
import com.example.demo.models.response.GetAvailableSeatsResponse;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CinemaRepository;
import com.example.demo.repositories.ShowRepository;
import com.example.demo.repositories.ShowSeatBookingRepository;
import com.example.demo.utils.DateTimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private CinemaRepository cinemaRepository;
    private ShowRepository showRepository;
    private AdminHelper adminHelper;
    private ShowSeatBookingRepository showSeatBookingRepository;
    private HallSeatDao hallSeatDao;
    private BookingRepository bookingRepository;
    private ShowSeatBookingDao showSeatBookingDao;

    public CinemaService(CinemaRepository cinemaRepository,ShowRepository showRepository,AdminHelper adminHelper,ShowSeatBookingRepository showSeatBookingRepository,HallSeatDao hallSeatDao,BookingRepository bookingRepository,ShowSeatBookingDao showSeatBookingDao){
        this.cinemaRepository = cinemaRepository;
        this.showRepository = showRepository;
        this.adminHelper = adminHelper;
        this.showSeatBookingRepository = showSeatBookingRepository;
        this.hallSeatDao = hallSeatDao;
        this.bookingRepository = bookingRepository;
        this.showSeatBookingDao = showSeatBookingDao;
    }


    public List<String> getCities(){
        return cinemaRepository.findAllCities();
    }

    public List<Cinema> getCinemasWithAShowOfAGivenMovie(String movieTitle){
        List<Integer> cinemaIds = showRepository.findCinemaIdByMovieTitle(movieTitle);
        Iterable<CinemaEntity> cinemaEntityIterable = cinemaRepository.findAllById(cinemaIds);
        Iterable<ShowsEntity> showsEntityIterable = showRepository.findByMovieTitle(movieTitle);

        List<CinemaEntity> cinemaEntityList = new ArrayList<>();
        cinemaEntityIterable.forEach(cinemaEntityList::add);
        List<ShowsEntity> showsEntityList = new ArrayList<>();
        showsEntityIterable.forEach(showsEntityList::add);

        return adminHelper.getCinemasFromEntities(cinemaEntityList, showsEntityList);
    }



    public GetAvailableSeatsResponse getAvailableSeats(String showId){
        GetAvailableSeatsResponse response = new GetAvailableSeatsResponse("",true);
        List<ShowSeatBookingEntity> showSeatBookingEntityList = showSeatBookingRepository.findAllByShowSeatBookingIdentity_ShowId(Integer.parseInt(showId));
        Set<Integer> seatIds = showSeatBookingEntityList.stream().filter(ShowSeatBookingEntity::getStatus).map(k->k.getShowSeatBookingIdentity().getSeatId()).collect(Collectors.toSet());
        List<CinemaHallSeat> hallSeatList = adminHelper.convertCinemaHallSeatEntityToObject(hallSeatDao.findAllByIds(seatIds));
        response.getAvailable().addAll(hallSeatList);
        return response;
    }
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public BookingResponse bookSeats(CreateBookingRequest bookingRequest) throws Exception {
        BookingResponse response = new BookingResponse(true,"");
        BookingEntity bookingEntity = bookingRepository.save(BookingEntity.builder().createdOn(DateTimeUtils.getCurrentDateTime()).noOfSeats(bookingRequest.getSeatIds().size()).status(BookingStatus.REQUESTED.name()).build());
        try {
            showSeatBookingDao.updateTable(bookingEntity.getId(), bookingRequest.getSeatIds(), bookingRequest.getShowId());
        }
        catch(Exception e){
            bookingEntity.setStatus(BookingStatus.FAILED.name());
            bookingRepository.save(bookingEntity);
            throw e;
        }
        bookingEntity.setStatus(BookingStatus.CONFIRMED.name());
        bookingRepository.save(bookingEntity);
        response.setBookingId(bookingEntity.getId());
        return response;
    }

}
