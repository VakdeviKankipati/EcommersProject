package com.vakya.productservicesfeb29.repositories;

import com.vakya.productservicesfeb29.models.Category;
import com.vakya.productservicesfeb29.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);

    @Override
    List<Product> findAll();

    Product findByIdIs(Long id);

    List<Product> findAllByTitle(String title);

    //Product updateProductBy();

    //Product deleteByIdIs(Long id);
    void delete(Product product);

    List<Product> findByCategory_Title(String title);

    List<Product> findByCategory(Category category);

    Page<Product> findAll(Pageable pageable);



}
