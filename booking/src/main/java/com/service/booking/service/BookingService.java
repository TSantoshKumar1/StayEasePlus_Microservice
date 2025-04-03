package com.service.booking.service;

import com.service.booking.dto.BookingDto;
import com.service.booking.entites.Booking;

public interface BookingService {

    // create a Booking...
    public Booking createBooking(BookingDto bookingDto);
}
