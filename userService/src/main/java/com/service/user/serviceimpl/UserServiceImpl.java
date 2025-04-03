package com.service.user.serviceimpl;

import com.service.user.entities.Hotel;
import com.service.user.entities.Rating;
import com.service.user.entities.User;
import com.service.user.exception.ResourceNotFoundException;
import com.service.user.externalService.HotelService;
import com.service.user.repository.UserRepository;
import com.service.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    private Logger logger =  LoggerFactory.getLogger(UserService.class);

    // create a user...
    @Override
    public User createUser(User user) {

      String userId =  UUID.randomUUID().toString();
      user.setUserId(userId);
      return userRepository.save(user);
    }


// get all users....
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        users.stream().map(user->{

            // api call to rating service to get rating by user
            //http://localhost:8082/rating/ratiingByuser/4689ad81-1271-4789-bb8c-1b89cd8be572

            Rating[] rating = restTemplate.getForObject("http://RATING-SERVICE/rating/ratiingByuser/"+user.getUserId(), Rating[].class);

            List<Rating> ratingWithList = Arrays.stream(rating).toList();
            logger.info("{} ",ratingWithList);

            ratingWithList.stream().map(rating1->{

                // api call to hotel service to get hotel by hotelId
       //         ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + rating1.getHotelId(), Hotel.class);

        //       grtHotel() is a declarative http web client that is  Feign client
                Hotel hotel = hotelService.getHotel(rating1.getHotelId());
                rating1.setHotel(hotel);

                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingWithList);

            return  user;

        }).collect(Collectors.toList());

        return users;
    }


    // get single user...
    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        //http://localhost:8082/rating/ratiingByuser/4689ad81-1271-4789-bb8c-1b89cd8be572

        // api call to Rating service to get rating by user

        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/ratiingByuser/" + user.getUserId(), Rating[].class);

        logger.info("{}",ratings);
        List<Rating> ratingList = Arrays.stream(ratings).toList();

         ratingList.stream().map(rating -> {

          // api call to hotel service to get the hotel

          // http://localhost:8083/hotel/0f2074a7-b3d6-4e8c-84b0-1de3a2a188d1
          ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
             rating.setHotel(hotel);
             return rating;
        }).collect(Collectors.toList());

             user.setRatings(ratingList);

             return user;

    }
}
