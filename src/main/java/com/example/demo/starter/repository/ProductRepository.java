package com.example.demo.starter.repository;

import com.example.demo.starter.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
    Optional<Product> getByName(String name);
}
