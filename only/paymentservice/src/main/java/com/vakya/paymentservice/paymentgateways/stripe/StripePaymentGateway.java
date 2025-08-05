package com.vakya.paymentservice.paymentgateways.stripe;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.vakya.paymentservice.paymentgateways.PaymentGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @Override
    public String generatePaymentLink(Long amount) throws StripeException {

        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams params =
                ProductCreateParams.builder().setName("GENERIC").build();
        Product product = Product.create(params);


        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)// amount*100=rs10==1000
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                   .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                           .setRedirect(
                                                   PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                           .setUrl("https://scaler.com").build()
                                           )
                                        .build())
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }

    @Override
    public String doPayment(String email, String phone, Long amount, String orderId) throws RazorpayException {
        return null;
    }
}
