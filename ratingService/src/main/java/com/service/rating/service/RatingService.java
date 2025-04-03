package com.service.rating.service;

import com.service.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    // create Rating...
    public Rating createRating(Rating rating);

    // get all Rating...
    public List<Rating> getAllRating();


    // get a single rating by userId....
    public List<Rating> getRatingByuserId(String userId );

    // get rating by hotelId...
    public List<Rating> getRatingByHotelId(String hotelId);
}
