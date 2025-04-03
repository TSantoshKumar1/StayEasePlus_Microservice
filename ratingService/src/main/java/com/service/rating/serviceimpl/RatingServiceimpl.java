package com.service.rating.serviceimpl;

import com.service.rating.entities.Rating;
import com.service.rating.repository.RatingRepository;
import com.service.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceimpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;



    @Override
    public Rating createRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {

         List<Rating> allRatings =   ratingRepository.findAll();
        return allRatings;
    }


    @Override
    public List<Rating> getRatingByuserId(String userId) {
        List<Rating> userratings =  ratingRepository.findByUserId(userId).orElseThrow(()->new RuntimeException("resource not found"));
        return userratings;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId).orElseThrow(()->new RuntimeException("resource not found"));
    }

}
