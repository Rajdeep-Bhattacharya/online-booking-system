package com.example.demo.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class BookingResponse extends BaseResponse {
    int bookingId;
    @Builder
    public BookingResponse(boolean response, String error) {
        super(response, error);
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
