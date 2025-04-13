package com.example.demo.starter.repository.product;

import com.example.demo.starter.entity.product.Product;
import com.example.demo.starter.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
    Optional<Product> getByName(String name);
}
