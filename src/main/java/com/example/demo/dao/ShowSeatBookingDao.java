package com.example.demo.dao;


import com.example.demo.domain.ShowSeatBookingEntity;
import com.example.demo.repositories.ShowSeatBookingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ShowSeatBookingDao {
    private ShowSeatBookingRepository showSeatBookingRepository;

    public ShowSeatBookingDao(ShowSeatBookingRepository showSeatBookingRepository){
        this.showSeatBookingRepository = showSeatBookingRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updateTable(int bookingId, List<Integer> seatIds,int showId) throws Exception {
        List<ShowSeatBookingEntity> showSeatBookingEntityList = showSeatBookingRepository.findAllByShowSeatBookingIdentity_SeatIdInAndShowSeatBookingIdentity_ShowId(seatIds,showId);
        if(showSeatBookingEntityList.stream().anyMatch(k -> !k.getStatus()) || showSeatBookingEntityList.size() == 0)
            throw new Exception("one or more seats already booked or seats are invalid please try again");
        showSeatBookingRepository.updateBookingId(seatIds,bookingId,showId);
    }



}
