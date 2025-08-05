package com.vakya.productservicesfeb29.services;

import com.vakya.productservicesfeb29.exceptions.ProductNotFoundException;
import com.vakya.productservicesfeb29.models.Category;
import com.vakya.productservicesfeb29.models.Product;
import com.vakya.productservicesfeb29.repositories.CategoryRepository;
import com.vakya.productservicesfeb29.repositories.ProductRepository;
//import org.hibernate.query.Page;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.net.URISyntaxException;
import java.util.List;
@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category category1 = (Category) categoryRepository.findByTitle(category);

        if(category1 == null){
            category1 = new Category();
            category1.setTitle(title);
        }
        product.setCategory(category1);


        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
        Product existingProduct = getSingleProduct(productId);
        if (product.getTitle() != null) {
            existingProduct.setTitle(product.getTitle());
        }
        if (product.getImageUrl() != null) {
            existingProduct.setImageUrl(product.getImageUrl());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (Double.valueOf(product.getPrice()) != 0.0) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getCategory() != null) {
            Category newCategory = new Category();
            newCategory.setTitle(product.getCategory().getTitle());
            existingProduct.setCategory(newCategory);
        }
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public Object getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public Product deleteProducts(Long productId) throws ProductNotFoundException {
        Product p = getSingleProduct(productId);
        productRepository.delete(p);
        return p;
    }

   /* @Override
    public Object getSpecificCategorys(String category) {
        Category cat = categoryRepository.findByTitle(category);
        return  productRepository.findByCategory(cat);
      //  return productRepository.findByCategory_Title(String.valueOf(cat));
    }
    */


    @Override
    public List<Product> getProducts(String category) throws ProductNotFoundException {
        Category cat = categoryRepository.findByTitle(category);
        return  productRepository.findByCategory(cat);
    }
    public Page<Product> getProductss(int numberOfProducts, int offset) {
        Page<Product> products = productRepository.findAll(
                PageRequest.of((offset/numberOfProducts),
                        numberOfProducts,
                        Sort.by("price").descending()
                                .and(Sort.by("title").ascending()
                        ))
        );
        return products;
    }


}
