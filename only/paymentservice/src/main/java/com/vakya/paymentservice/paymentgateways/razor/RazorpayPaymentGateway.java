package com.vakya.paymentservice.paymentgateways.razor;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vakya.paymentservice.paymentgateways.PaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;

public class RazorpayPaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(Long amount) throws StripeException {
        return null;
    }
    private RazorpayClient razopayClient;

    public  RazorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razopayClient = razorpayClient;
    }
    @Override
    public String doPayment(String email, String phone, Long amount, String orderId) throws RazorpayException{
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("receipt", orderId);

            JSONObject customer = new JSONObject();
            customer.put("email", email);
            customer.put("phone", phone);
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);

            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("callback_url", "http://domain.com/razorpayWebHook");
            paymentLinkRequest.put("callback_method", "post");
            PaymentLink response = razopayClient.paymentLink.create(paymentLinkRequest);
            // PaymentLink response = razopayClient.paymentLink.create(paymentLinkRequest);
            // orderRequest.put("notes",notes);

            return response.toString();
        }catch (Exception e){
            e.printStackTrace();;
        }
        return null;
    }
}
