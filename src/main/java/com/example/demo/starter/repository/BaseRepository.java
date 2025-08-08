package com.example.demo.starter.repository;

import com.example.demo.starter.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseRepository<D extends BaseEntity> extends JpaRepository<D, UUID> {
}
