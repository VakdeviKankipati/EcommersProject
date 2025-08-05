package com.vakya.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDto {
    private Long productId;
    private int quantity;

}
