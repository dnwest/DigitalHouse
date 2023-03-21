package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.ProductEntity;
import com.grupo4.api_alucar.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public ProductEntity save(ProductEntity productEntity){
        repository.save(productEntity);
        return productEntity;
    }

    public List<ProductEntity> getAll(){
        return repository.findAll();
    }

    public Optional<ProductEntity> getById(Long id){
        return repository.findById(id);
    }

    public ProductEntity update(ProductEntity productEntity){
        repository.save(productEntity);
        return productEntity;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<ProductEntity> findProductsByLocation(Long locationId) {
        return repository.findProductsByLocation(locationId);
    }

    public List<ProductEntity> findProductsByCategory(Long categoryId) {
        return repository.findProductsByCategory(categoryId);
    }
}
