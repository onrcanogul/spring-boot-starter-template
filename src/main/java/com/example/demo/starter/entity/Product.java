package com.example.demo.starter.entity;

import com.example.demo.starter.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "products")
@SQLRestriction("is_deleted = false")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private double price;
}
