package com.service.order.service;

import com.service.order.dto.OrderDto;
import com.service.order.entities.Order;

public interface OrderService {


    // create order of booking.....
    public Order createOrder(OrderDto orderDto);
}
