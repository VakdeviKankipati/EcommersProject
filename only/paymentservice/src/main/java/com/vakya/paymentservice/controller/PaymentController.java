package com.vakya.paymentservice.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vakya.paymentservice.dto.CreatePaymentLinkRequestDto;
import com.vakya.paymentservice.dto.InitiatePaymentRequestDto;
import com.vakya.paymentservice.service.PaymentServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    //@Autowired//field injection
   // private PaymentService paymentService;
    //constructor injection
    //public PaymentController(PaymentService paymentService){
     //   this.paymentService = paymentService;
    //}

   /* private PaymentService razorpayPaymentServices;
    private PaymentService stripePaymentServices;
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;*/
    private PaymentServices paymentServices;
    private PaymentController(/*@Qualifier("stripe")*/ PaymentServices paymentServices){

        this.paymentServices = paymentServices;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto) throws StripeException {
        return paymentServices.createPaymentLink(requestDto.getOrderId());
    }
    public String intiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto)throws RazorpayException {
        return paymentServices.intiatePayment(paymentRequestDto.getEmail(),
                paymentRequestDto.getPhoneNumber(),
                paymentRequestDto.getAmount(),
                paymentRequestDto.getOrderId()
                );
    }

  /*  public PaymentController(
            @Qualifier("razorpay") PaymentService razorpayPaymentServices,
            @Qualifier("stripe") PaymentService stripePaymentServices,
            PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy){
        this.razorpayPaymentServices = razorpayPaymentServices;
        this.stripePaymentServices = stripePaymentServices;
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    @PostMapping("/payment")
    public String intiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException {
        int paymentGatewayOption = choosePaymentGateway();
        switch (paymentGatewayOption){
            case 1 : return razorpayPaymentServices.doPayment(requestDto.getEmail(),
                    requestDto.getPhoneNumber(),
                    requestDto.getAmount(),
                    requestDto.getOrderId()
            );
            case 2 : return  stripePaymentServices.doPayment(requestDto.getEmail(),
                    requestDto.getPhoneNumber(),
                    requestDto.getAmount(),
                    requestDto.getOrderId()
            );
        }
       // return  paymentService.doPayment(requestDto.getEmail(),
        //        requestDto.getPhoneNumber(),
          //      requestDto.getAmount(),
          //      requestDto.getOrderId()
            //    );
        //return null;
    }
    private  int choosePaymentGateway(){
        return paymentGatewaySelectionStrategy.paymentGatewaySelection();
    }*/




}
