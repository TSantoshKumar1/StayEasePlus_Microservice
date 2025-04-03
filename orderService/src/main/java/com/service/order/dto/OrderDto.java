package com.service.order.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class OrderDto {

    private String bookingId;

    private  double amount;

    private String orderStatus;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "bookingId='" + bookingId + '\'' +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
