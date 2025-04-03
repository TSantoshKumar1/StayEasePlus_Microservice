package com.service.payment.payment.controller;


import com.razorpay.RazorpayException;
import com.service.payment.payment.dto.VerifyPaymentDto;
import com.service.payment.payment.entities.Payment;
import com.service.payment.payment.paymentVerifyService.PaymentVerificationService;
import com.service.payment.payment.repository.PaymentRepository;
import com.service.payment.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentVerificationService paymentVerificationService;
    @Autowired
    private PaymentRepository paymentRepository;


    // create a payment...
    @PostMapping("/{orderId}/{amount}")
    public ResponseEntity<Payment>createPayment(@PathVariable String orderId , @PathVariable double amount){

        Payment payment = paymentService.createPayment(orderId, amount);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    // verify a payment after response send backe to front-end by razorpay...

    @PostMapping("/verifyPayment")
    public ResponseEntity<?>verifyPayment(@RequestBody VerifyPaymentDto verifyPaymentDto) throws RazorpayException {

        boolean verifyPayment = paymentVerificationService.verifyPayment(verifyPaymentDto);
        if(verifyPayment){
            // if payment verify then add payment details in payment table..
            Payment payment = new Payment();
            payment.setPaymentStatus("success");
            payment.setRazorPayPaymentId(verifyPaymentDto.getRazorPayPaymentId());
            payment.setRazorpaySignature(verifyPaymentDto.getRazorpaySignature());

            paymentRepository.save(payment);

            return new ResponseEntity<>("payment  verified successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("payment verification failed",HttpStatus.CONFLICT);
        }
    }



}
