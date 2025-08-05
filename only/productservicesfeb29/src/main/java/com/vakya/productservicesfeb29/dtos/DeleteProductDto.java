package com.vakya.productservicesfeb29.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}
