package com.group4.alucar.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.dto.CategoryDto;
import com.group4.alucar.model.CategoryEntity;
import com.group4.alucar.repository.CarRepository;
import com.group4.alucar.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntity update(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryEntity> getAllCategoriesByLocation(Long locationId) {
        return categoryRepository.findAllCategoriesByLocation(locationId);
    }

    public List<CategoryEntity> getAvailableCategoriesByLocation(Long locationId) {
        return categoryRepository.findAvailableCategoriesByLocation(locationId);
    }

    public List<CategoryDto> getAllCategoriesAndCarsByLocation(Long locationId) {
        return CategoryDto.fromCars(carRepository.findAllCarsAndCategoriesByLocation(locationId));
    }

    public List<CategoryDto> getAvailableCategoriesAndCarsByLocation(Long locationId) {
        return CategoryDto.fromCars(carRepository.findAvailableCarsAndCategoriesByLocation(locationId));
    }

    protected BigDecimal getCategoryPrice(Long categoryId) {
        return categoryRepository.getCategoryPrice(categoryId);
    }
}
