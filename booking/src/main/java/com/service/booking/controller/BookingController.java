package com.service.booking.controller;

import com.service.booking.dto.BookingDto;
import com.service.booking.entites.Booking;
import com.service.booking.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.awt.print.Book;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;



    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto){

        Booking booking = bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);


    }


}
