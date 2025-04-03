package com.service.payment.payment.service;

import com.service.payment.payment.entities.Payment;

public interface PaymentService {

    // create a payment for order....

    public Payment createPayment(String orderId, double amount);
}
