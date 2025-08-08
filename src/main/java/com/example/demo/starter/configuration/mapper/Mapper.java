package com.example.demo.starter.configuration.mapper;

import org.modelmapper.ModelMapper;

public class Mapper<E, D> implements GenericMapper<E, D> {
    private final ModelMapper modelMapper = new ModelMapper();
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public Mapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }
}
