package com.vakya.paymentservice.controller.webhooks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpayWebHook")
public class RazorpayWebHookController {
    @PostMapping("/")
    public ResponseEntity acceptWebHookRequest(){
        return null;
    }

}
