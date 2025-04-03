package com.service.order.serviceimpl;

import com.service.order.dto.OrderDto;
import com.service.order.dto.PaymentDto;
import com.service.order.entities.Order;
import com.service.order.repository.OrderRepository;
import com.service.order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

// create  order of booking.....
    @Override
    @CircuitBreaker(name = "paymentServiceBreaker",fallbackMethod = "paymentServiceFallback")
    @Retry(name="paymentService",fallbackMethod ="paymentServiceFallback")
    @RateLimiter(name="orderRatelimiter",fallbackMethod ="paymentServiceFallback")
    public Order createOrder(OrderDto orderDto) {

        Order order = modelMapper.map(orderDto, Order.class);
        String orderId =  UUID.randomUUID().toString();
        order.setOrderId(orderId);
        Order orders = orderRepository.save(order);

     // api call to Create payment of paymentService by using restTemplate....
        //http://PAYMENT-SERVICE/payment/orderId/amount;
        PaymentDto paymentDto = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/" + orders.getOrderId() + "/" + orders.getAmount(), null, PaymentDto.class);
        orders.setPaymentDto(paymentDto);
        orders.setOrderStatus("Success");
        Order saveOrders = orderRepository.save(orders);
        return saveOrders;
    }



    // fall back method is executed when service down while call payment service from order service...
     public ResponseEntity<OrderDto>paymentServiceFallback(OrderDto orderDto,Exception exception){

        logger.info("this is paymentServiceFallback method executed because service down", exception.getMessage());
         OrderDto orderDto1 = new OrderDto();
         orderDto1.setAmount(000.00);
         orderDto1.setBookingId("dummy123");
         orderDto1.setOrderStatus("dummy0000");

         return new ResponseEntity<>(orderDto, HttpStatus.BAD_REQUEST);

     }


}
