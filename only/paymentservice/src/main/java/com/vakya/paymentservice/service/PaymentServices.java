package com.vakya.paymentservice.service;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.vakya.paymentservice.dto.InitiatePaymentRequestDto;
import com.vakya.paymentservice.paymentgateways.razor.RazorpayPaymentGateway;
import com.vakya.paymentservice.paymentgateways.stripe.StripePaymentGateway;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentServices {
    private StripePaymentGateway stripePaymentGateway;
    private RazorpayPaymentGateway razorpayPaymentGateway;
    public PaymentServices(StripePaymentGateway stripePaymentGateway){
        this.stripePaymentGateway = stripePaymentGateway;
        //this.razorpayPaymentGateway = razorpayPaymentGateway;
    }
   /* public  PaymentServices(RazorpayPaymentGateway razorpayPaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
    }*/

    public String createPaymentLink(Long orderId) throws StripeException {
        return stripePaymentGateway.generatePaymentLink(10000L);
    }
    public String intiatePayment(String email,String phone,Long amount, String orderId) throws RazorpayException {
        return null;
    }
}
