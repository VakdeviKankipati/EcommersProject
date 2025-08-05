package com.vakya.orderservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "`order`")
public class Order extends BaseModel{
    private Long productId;
    private int quantity;
    private double totalPrice;
}
