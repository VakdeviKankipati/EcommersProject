package com.vakya.productservicesfeb29.services;

import com.vakya.productservicesfeb29.exceptions.ProductNotFoundException;
import com.vakya.productservicesfeb29.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.net.URISyntaxException;
import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product createProduct(  String title,
                            String description,
                            String category,
                            double price,
                            String image
    );
    //Object g
    Product updateProduct(Long productId,Product product) throws ProductNotFoundException;

    Object getAllCategories();

    Product deleteProducts(Long productId) throws  ProductNotFoundException;


    //Object getSpecificCategorys(String category);
    List<Product> getProducts(String category) throws ProductNotFoundException;

    Page<Product> getProductss(int numberOfProducts, int offset) ;



}
