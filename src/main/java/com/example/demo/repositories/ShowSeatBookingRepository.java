package com.example.demo.repositories;

import com.example.demo.domain.ShowSeatBookingEntity;
import com.example.demo.domain.ShowSeatBookingIdentity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public interface ShowSeatBookingRepository extends CrudRepository<ShowSeatBookingEntity, ShowSeatBookingIdentity> {

    List<ShowSeatBookingEntity> findAllByShowSeatBookingIdentity_ShowId(int showId);

    List<ShowSeatBookingEntity> findAllByShowSeatBookingIdentity_SeatIdInAndShowSeatBookingIdentity_ShowId(List<Integer> seatIds,int showId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update show_seat_booking_table ssbt set ssbt.status = false , ssbt.booking_id = :bookingId  where ssbt.seat_id in :seatIds and ssbt.show_id = :showId and ssbt.status = true", nativeQuery = true)
    void updateBookingId(@Param("seatIds") List<Integer> seatIds, @Param("bookingId") int bookingId,@Param("showId") int showId);
}
