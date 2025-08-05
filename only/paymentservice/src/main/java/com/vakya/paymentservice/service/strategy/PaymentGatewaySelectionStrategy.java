package com.vakya.paymentservice.service.strategy;

import org.springframework.stereotype.Service;


public interface PaymentGatewaySelectionStrategy {
    int paymentGatewaySelection();
}
