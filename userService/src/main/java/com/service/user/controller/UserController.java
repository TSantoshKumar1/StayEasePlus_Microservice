package com.service.user.controller;

import com.service.user.entities.User;
import com.service.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);




    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user){

        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/alluser")
    public ResponseEntity<List<User>> getAllUsers(){

       List<User> allusers = userService.getAllUser();
       return new ResponseEntity<>(allusers,HttpStatus.OK);
    }

    int retryCount =1;


    @GetMapping("/{userId}")
   // @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService",fallbackMethod ="ratingHotelFallback")
   @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){

        logger.info("retry count: {}",+ retryCount);
        retryCount++;

       User user= userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

// fallback method whic is executed when one of service is down during call user service.....

    public ResponseEntity<User> ratingHotelFallback(String userId , Exception ex){

        logger.info("fallback is executed because service is down ",ex.getMessage());
        ex.printStackTrace();

        User user = new User();
        user.setUserId("1234");
        user.setName("dummy");
        user.setEmail("dummy@gmail.com");
        user.setAbout("this user is created dummy because server is down");
        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);

    }


}
