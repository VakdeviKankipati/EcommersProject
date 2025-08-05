package com.vakya.productservicesfeb29.controllers;


import com.vakya.productservicesfeb29.models.Product;
import com.vakya.productservicesfeb29.services.ProductService;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("selfProductService")
    private ProductService productService;
    private List<Product> products;

    @Test
    void getProductss() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void getProductDetails() {
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setTitle("Samsung");
        product1.setDescription("Best Phone");
        product1.setPrice(100.0);
        product1.setImageUrl("image1.jpg");

        Product product2 = new Product();
        product2.setTitle("Vivo");
        product2.setDescription("Good phone");
        product2.setPrice(200.0);
        product2.setImageUrl("image2.jpg");

        products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void deleteProducts() {
    }

    @Test
    void getProducts() {
    }








}