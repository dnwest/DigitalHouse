package com.group4.alucar.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.alucar.model.CarEntity;
import com.group4.alucar.model.CategoryEntity;
import com.group4.alucar.model.FeatureEntity;
import com.group4.alucar.model.ImageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) 
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ImageEntity> images;
    private List<FeatureEntity> features;
    private List<CarEntity> cars;

    public static List<CategoryDto> fromCars(List<CarEntity> carEntities) {
        Map<CategoryEntity, List<CarEntity>> categoryMap = new HashMap<>();
        List<CategoryDto> categories = new ArrayList<>();
        
        carEntities.forEach(car -> {
            categoryMap.put(car.getCategory(), new ArrayList<>());
        });

        carEntities.forEach(car -> {
            categoryMap.get(car.getCategory()).add(car);
        });

        categoryMap.forEach((category, cars) -> {
            categories.add(
                CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .price(category.getPrice())
                .images(category.getImages())
                .features(category.getFeatures())
                .cars(cars)
                .build()
            );
        });
        return categories;
    }
}
