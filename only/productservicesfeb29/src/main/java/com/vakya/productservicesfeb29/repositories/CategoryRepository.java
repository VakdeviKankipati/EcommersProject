package com.vakya.productservicesfeb29.repositories;

import com.vakya.productservicesfeb29.models.Category;
import com.vakya.productservicesfeb29.models.Product;
//import jdk.jfr.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);

    @Override
    List<Category> findAll();

}
