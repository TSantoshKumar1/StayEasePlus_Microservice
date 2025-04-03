package com.service.hotel.controller;

import com.service.hotel.entities.Hotel;
import com.service.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // create a hotel
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
      public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
             Hotel createdHotel =   hotelService.createHotel(hotel);
             return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    // get all hotels
    @PreAuthorize("hasAuthorize('scope_internal')|| hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>>getAllHotels(){

        List<Hotel> allHotels = hotelService.getAllHotel();
        return new ResponseEntity<>(allHotels, HttpStatus.OK);
    }

    // get a single hotel
    @PreAuthorize("hasAuthority('sope_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel>getHotel(@PathVariable String hotelId){

       Hotel singleHotel =   hotelService.getHotel(hotelId);
        return new ResponseEntity<>(singleHotel, HttpStatus.OK);
    }

}
