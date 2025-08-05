package com.vakya.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(Long amount) throws StripeException;

    String doPayment(String email, String phone, Long amount, String orderId) throws RazorpayException;
}
