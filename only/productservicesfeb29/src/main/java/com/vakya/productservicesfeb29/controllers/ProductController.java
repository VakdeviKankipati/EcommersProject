package com.vakya.productservicesfeb29.controllers;

import com.vakya.productservicesfeb29.dtos.*;
import com.vakya.productservicesfeb29.exceptions.InvalidTokenException;
import com.vakya.productservicesfeb29.exceptions.ProductNotFoundException;
import com.vakya.productservicesfeb29.models.Category;
import com.vakya.productservicesfeb29.models.Product;
import com.vakya.productservicesfeb29.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService,
                             RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate=restTemplate;
    }
    @GetMapping("/products/pagination")
    public ResponseEntity<Page<Product>> getProductss(@RequestBody GetProductRequestDto requestDto)  {
        return ResponseEntity.of(Optional.ofNullable(productService.getProductss(
                requestDto.getNumberOfResults() , requestDto.getOffset()
        )));
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImage()
        );
    }


    @GetMapping("/products/{id}/{token}")
    public Product getProductDetails(@PathVariable("id") Long productsId, @PathVariable("token") String token) throws ProductNotFoundException, InvalidTokenException {
        /*UserDto userDto = authenticationCommons.validateToken(token);
        if(userDto==null){
            throw new InvalidTokenException("token not found");
        }*/
        UserDto userDto =
                restTemplate.getForObject("http://userservice/users/1", UserDto.class);



        return productService.getSingleProduct(productsId);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody UpdateProductRequestDto request) throws ProductNotFoundException{
        Product product = new Product();
        product.setDescription(request.getDescription());
        Category category = new Category();
        category.setTitle(request.getCategory());
        product.setImageUrl(request.getImage());
        if (category.getTitle() != null) {
            product.setCategory(category);
        }
        product.setTitle(request.getTitle());
        if(request.getPrice()!=0.0){
            product.setPrice(request.getPrice());
        }
        return productService.updateProduct(productId, product);

    }

  /*  @GetMapping("products")
    public Object getAllProduct() {
        //return productService.getAllProducts();
        return productService.getAllProduct();
    }*/

    @GetMapping("/products/categories")
    public Object getAllCategories(){

        return productService.getAllCategories();
    }

    @DeleteMapping("/products/{productId}")
    public Product deleteProducts(@PathVariable("productId") Long productId) throws ProductNotFoundException{
        return productService.deleteProducts(productId);
    }

   /* @GetMapping("/products/category/category")
    public Object getSpecificCategorys(@PathVariable("category") String category){
        return  productService.getSpecificCategorys(category);
    }
    */
    @GetMapping("/products/category/{category}")
    public  List<Product> getProducts(@PathVariable("category") String category) throws  ProductNotFoundException{
        return  productService.getProducts(category);
    }


}
