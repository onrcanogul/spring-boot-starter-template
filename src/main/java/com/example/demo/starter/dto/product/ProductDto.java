package com.example.demo.starter.dto.product;

import com.example.demo.starter.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDto extends BaseDto {
    private String name;
    private String description;
    private double price;
}
