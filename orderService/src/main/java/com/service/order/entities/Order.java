package com.service.order.entities;

import com.service.order.dto.PaymentDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Order {

    @Id
    private String orderId;
    @Column(nullable = false)
    private String bookingId;
    @Column(nullable = false)
    private  double amount;
    @Column(nullable = false)
    private String orderStatus;

    @Transient
    private PaymentDto paymentDto;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public PaymentDto getPaymentDto() {
        return paymentDto;
    }

    public void setPaymentDto(PaymentDto paymentDto) {
        this.paymentDto = paymentDto;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                ", paymentDto=" + paymentDto +
                '}';
    }
}
