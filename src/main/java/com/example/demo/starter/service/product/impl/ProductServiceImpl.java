package com.example.demo.starter.service.product.impl;

import com.example.demo.starter.configuration.mapper.Mapper;
import com.example.demo.starter.dto.product.ProductDto;
import com.example.demo.starter.entity.Product;
import com.example.demo.starter.repository.ProductRepository;
import com.example.demo.starter.service.base.impl.BaseServiceImpl;
import com.example.demo.starter.service.product.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductDto> implements ProductService {
    public ProductServiceImpl(ProductRepository repository, Mapper<Product, ProductDto> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
    }
}
