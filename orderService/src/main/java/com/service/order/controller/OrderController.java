package com.service.order.controller;


import com.service.order.dto.OrderDto;
import com.service.order.entities.Order;
import com.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")

    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){

        Order order = orderService.createOrder(orderDto);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);

    }
}
