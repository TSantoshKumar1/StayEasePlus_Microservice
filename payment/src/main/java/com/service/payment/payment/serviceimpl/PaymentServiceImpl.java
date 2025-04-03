package com.service.payment.payment.serviceimpl;

import com.razorpay.Order;
import com.service.payment.payment.entities.Payment;
import com.service.payment.payment.razarPayService.RazarPayService;
import com.service.payment.payment.repository.PaymentRepository;
import com.service.payment.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
  
    @Autowired
    private RazarPayService razarPayService;
    


    // create a payment for order before verify payment......

    @Override
    public Payment createPayment(String orderId , double amount) {
       String paymentId = UUID.randomUUID().toString();
        Order razorPayOrder = razarPayService.createRazorPayOrder(amount, orderId);
        
        // Razorpay provide orderObject details add in payment class object 

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setRazorPayOrderId(razorPayOrder.get("id").toString());
        // save the payment in table 
        Payment savePayment = paymentRepository.save(payment);

        return savePayment;
    }
}
