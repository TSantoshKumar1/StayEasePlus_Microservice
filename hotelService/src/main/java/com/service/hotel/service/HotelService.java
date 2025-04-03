package com.service.hotel.service;

import com.service.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create Hotel
    public Hotel createHotel(Hotel hotel);

    // get all hotels
    public List<Hotel> getAllHotel();

    // get a single hotel
    public Hotel getHotel(String hotelId);
}
