package com.service.payment.payment.razarPayService;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.service.payment.payment.entities.Payment;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazarPayService {


    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;


    public Order createRazorPayOrder(double amount , String orderId){

        try{

            RazorpayClient client = new RazorpayClient(apiKey, apiSecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount",(int)(amount*100));  // convert to paisa...
            orderRequest.put("currency","INR");
            orderRequest.put("receipt",orderId);

            Order order = client.orders.create(orderRequest);

            return order;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
