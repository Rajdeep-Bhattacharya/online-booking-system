package com.example.demo.helper;

import com.example.demo.domain.CinemaEntity;
import com.example.demo.domain.CinemaHallEntity;
import com.example.demo.domain.CinemaHallSeatEntity;
import com.example.demo.domain.ShowsEntity;
import com.example.demo.models.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdminHelper {

    public List<Cinema> convertCinemaEntityToObject(List<CinemaEntity> cinemaEntityList, Map<String,List<ShowsEntity>> hallNameToShowsMap){
        List<Cinema> response = new ArrayList<>();
        for (CinemaEntity cinemaEntity : cinemaEntityList) {
            //convert entity to cinema object
            Cinema c = Cinema.builder()
                    .location(Address.builder()
                            .addressLine(cinemaEntity
                                    .getAddressLine())
                            .city(cinemaEntity.getCity())
                            .pin(cinemaEntity.getPin())
                            .state(cinemaEntity.getState()).build())
                    .name(cinemaEntity.getName())
                    .build();
            //fetch cinemahalls and add to cinema hall list for each cinema
            c.setHalls(convertCinemaHallEntityToObject(cinemaEntity.getHalls(),hallNameToShowsMap));
            c.setTotalCinemaHalls(c.getHalls().size());
            response.add(c);
        }
        return response;
    }



    public List<CinemaHall> convertCinemaHallEntityToObject(List<CinemaHallEntity> hallEntityList, Map<String,List<ShowsEntity>> hallNameToShowsMap){
        List<CinemaHall> hallList = new ArrayList<>();
        for(CinemaHallEntity hallEntity : hallEntityList){
            CinemaHall hall = CinemaHall.builder()
                    .name(hallEntity.getName())
                    .seats(convertCinemaHallSeatEntityToObject(hallEntity.getSeats()))
                    .shows(convertShowEntityToObject(hallNameToShowsMap.getOrDefault(hallEntity.getName(),new ArrayList<>())))
                    .build();
            hall.setTotalSeats(hall.getSeats().size());
            if(hall.getShows().size() > 0)
                hallList.add(hall);
        }
        return hallList;
    }

    public List<CinemaHallSeat> convertCinemaHallSeatEntityToObject(List<CinemaHallSeatEntity> entityList){
        List<CinemaHallSeat> hallSeatList = new ArrayList<>();
        for(CinemaHallSeatEntity seatEntity : entityList){
            CinemaHallSeat seat = new CinemaHallSeat();
            seat.setSeatCol(seatEntity.getSeatCol());
            seat.setSeatRow(seatEntity.getSeatRow());
            seat.setPrice(seatEntity.getPrice());
            seat.setSeatId(seatEntity.getId());
            hallSeatList.add(seat);
        }
        return hallSeatList;
    }

    public List<Show> convertShowEntityToObject(List<ShowsEntity> entityList){
        List<Show> showList = new ArrayList<>();
        for(ShowsEntity entity : entityList){
            Show s = Show.builder()
                    .cinemaHallName(entity.getCinemaHallName())
                    .created(entity.getCreated().toString())
                    .movieTitle(entity.getMovieTitle())
                    .endTime(entity.getEndTime().toString())
                    .startTime(entity.getStartTime().toString())
                    .id(entity.getId())
                    .build();
            showList.add(s);
        }

        return showList;

    }

    public List<Cinema> getCinemasFromEntities(List<CinemaEntity> cinemaEntityList, List<ShowsEntity> showsEntityList) {
        Map<String,List<ShowsEntity>> hallNameToShowsMap = new HashMap<>();
        for(ShowsEntity e : showsEntityList){
            hallNameToShowsMap.computeIfAbsent(e.getCinemaHallName(),k->new ArrayList<>()).add(e);
        }

        return convertCinemaEntityToObject(cinemaEntityList,hallNameToShowsMap);
    }

}
