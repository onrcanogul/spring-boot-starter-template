package com.example.demo.starter.entity.user;

import com.example.demo.starter.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
@Where(clause = "is_deleted = false")
public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
}
