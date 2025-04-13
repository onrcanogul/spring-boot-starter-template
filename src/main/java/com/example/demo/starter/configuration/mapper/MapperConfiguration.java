package com.example.demo.starter.configuration.mapper;

import com.example.demo.starter.dto.base.BaseDto;
import com.example.demo.starter.entity.base.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<BaseEntity, BaseDto> baseMapper() {
        return new Mapper<>(BaseEntity.class, BaseDto.class);
    }
}