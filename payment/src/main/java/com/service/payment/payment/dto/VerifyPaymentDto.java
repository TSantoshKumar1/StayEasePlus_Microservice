package com.service.payment.payment.dto;

public class VerifyPaymentDto {

    private String razorPayOrderId;
    private String razorPayPaymentId;
    private String razorpaySignature;

    public String getRazorPayOrderId() {
        return razorPayOrderId;
    }

    public void setRazorPayOrderId(String razorPayOrderId) {
        this.razorPayOrderId = razorPayOrderId;
    }

    public String getRazorPayPaymentId() {
        return razorPayPaymentId;
    }

    public void setRazorPayPaymentId(String razorPayPaymentId) {
        this.razorPayPaymentId = razorPayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    @Override
    public String toString() {
        return "verifyPaymentDto{" +
                "razorPayOrderId='" + razorPayOrderId + '\'' +
                ", razorPayPaymentId='" + razorPayPaymentId + '\'' +
                ", razorpaySignature='" + razorpaySignature + '\'' +
                '}';
    }
}
