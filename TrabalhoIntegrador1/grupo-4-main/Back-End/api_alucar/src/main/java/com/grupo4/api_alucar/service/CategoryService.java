package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.CategoryEntity;
import com.grupo4.api_alucar.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public CategoryEntity save(CategoryEntity categoryEntity){
        repository.save(categoryEntity);
        return categoryEntity;
    }

    public List<CategoryEntity> getAll(){
        return repository.findAll();
    }

    public Optional<CategoryEntity> getById(Long id){
        return repository.findById(id);
    }

    public CategoryEntity update(CategoryEntity categoryEntity){
        repository.save(categoryEntity);
        return categoryEntity;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
