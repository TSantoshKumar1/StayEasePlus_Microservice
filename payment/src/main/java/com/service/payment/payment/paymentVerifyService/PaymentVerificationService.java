package com.service.payment.payment.paymentVerifyService;

import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.service.payment.payment.dto.VerifyPaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentVerificationService {

    @Value("${razorpay.api.key}")
    private String apiKey;
    @Value("${razorpay.api.secret}")
    private String apiSecret;


    // verify payment ....
      public boolean verifyPayment(VerifyPaymentDto verifyPaymentDto) throws RazorpayException {

          String razorpayOrderId = verifyPaymentDto.getRazorPayOrderId();
          String razorpayPaymentId = verifyPaymentDto.getRazorPayPaymentId();
          String razorpaySignature = verifyPaymentDto.getRazorpaySignature();

          String payload =  razorpayOrderId + '|' + razorpayPaymentId;

          boolean isValidSignature = Utils.verifySignature(payload, razorpaySignature, apiSecret);
          return isValidSignature;


          }
      }

