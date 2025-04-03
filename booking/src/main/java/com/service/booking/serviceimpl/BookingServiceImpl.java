package com.service.booking.serviceimpl;

import com.service.booking.dto.BookingDto;
import com.service.booking.dto.OrderDto;
import com.service.booking.entites.Booking;
import com.service.booking.repository.BookingRepository;
import com.service.booking.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    // create  a Booking....
    @Override
    @CircuitBreaker(name="orderPaymentBreaker",fallbackMethod = "orderPaymentFallback")
    @Retry(name="orderPaymentService",fallbackMethod = "orderPaymentFallback")
    public Booking createBooking(BookingDto bookingDto) {

        Booking booking = modelMapper.map(bookingDto, Booking.class);
        String  bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);
        bookingRepository.save(booking);

 // api call to order service to create a order  of ookingId by using RestTemplate..
        OrderDto orderDto = new OrderDto();
         orderDto.setBookingId(booking.getBookingId());
         orderDto.setAmount(booking.getAmount());
        String orderServiceUrl = "http://ORDER-SERVICE/order/create";
        OrderDto orderDto1 = restTemplate.postForObject(orderServiceUrl, orderDto, OrderDto.class);

        // set the orderDto into booking entity

        booking.setOrderDto(orderDto1);
        booking.setStatus("Success");
        bookingRepository.save(booking);

        return booking;
    }


    // fallback method which executed when service is down while call booking service....
    public ResponseEntity<Booking> orderPaymentFallback(BookingDto bookingDto , Exception exception){

        logger.info("fallback method is executed because service is down",exception.getMessage());
        Booking booking = new Booking();
        booking.setBookingId("dummy123");
        booking.setUserId("dumy012");
        booking.setHotelId("dummy99999");
        booking.setAmount(500.00);
        booking.setNumberOfGuests(0);
        booking.setStatus("false");
        return new ResponseEntity<>(booking, HttpStatus.BAD_REQUEST);

    }
}
