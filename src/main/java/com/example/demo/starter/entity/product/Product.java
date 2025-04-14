package com.example.demo.starter.entity.product;

import com.example.demo.starter.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private double price;
}
