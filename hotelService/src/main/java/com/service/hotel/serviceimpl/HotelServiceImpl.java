package com.service.hotel.serviceimpl;

import com.service.hotel.entities.Hotel;
import com.service.hotel.repository.HotelRepository;
import com.service.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
       String hotelId =  UUID.randomUUID().toString();
       hotel.setHotelId(hotelId);
       return hotelRepository.save(hotel);
    }



    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }


    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new RuntimeException("Resource not found"));
    }
}
