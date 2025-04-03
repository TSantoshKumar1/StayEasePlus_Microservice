package com.service.rating.repository;

import com.service.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

     Optional<List<Rating>>findByUserId(String userId);

     Optional<List<Rating>>findByHotelId(String hotelId);
}
