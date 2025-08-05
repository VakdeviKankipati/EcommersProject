package com.vakya.paymentservice.controller.webhooks;

import com.razorpay.Webhook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebHookController {

    @GetMapping("/")
    public void handleWebhookRequest(Webhook webhook){

    }
}
