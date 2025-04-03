package com.service.booking.entites;

import com.service.booking.dto.OrderDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Booking {

    @Id
    private String bookingId;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String hotelId;
    @Column(nullable = false)
    private LocalDateTime checkedInDate;
    @Column(nullable = false)
    private LocalDateTime checkOutDate;
    @Column(nullable = false)
    private Integer numberOfGuests;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String status;

    @Transient
    private OrderDto orderDto;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getCheckedInDate() {
        return checkedInDate;
    }

    public void setCheckedInDate(LocalDateTime checkedInDate) {
        this.checkedInDate = checkedInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", checkedInDate=" + checkedInDate +
                ", checkOutDate=" + checkOutDate +
                ", numberOfGuests=" + numberOfGuests +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", orderDto=" + orderDto +
                '}';
    }
}
