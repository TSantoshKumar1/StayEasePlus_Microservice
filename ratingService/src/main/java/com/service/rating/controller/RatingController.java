package com.service.rating.controller;

import com.service.rating.entities.Rating;
import com.service.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating>createRating(@RequestBody Rating rating){

      Rating createdRating =   ratingService.createRating(rating);
      return new ResponseEntity<>(createdRating, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAuthority('scope_internal') || hasAuthority('Admin')")
    @GetMapping("/allratings")
    public ResponseEntity<List<Rating>>getAllRating(){
       List<Rating>  allratings = ratingService.getAllRating();
       return new ResponseEntity<>(allratings,HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('scope_internal')|| hasAuthority('Admin')")
    @GetMapping("/ratiingByuser/{userId}")
    public ResponseEntity<List<Rating>>getRatingByUser( @PathVariable  String userId){

        List<Rating> ratingByuserId = ratingService.getRatingByuserId(userId);
        return new ResponseEntity<>(ratingByuserId,HttpStatus.OK);

    }


    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable String hotelId){

        List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratingByHotelId,HttpStatus.OK);

    }



}
