package com.group4.alucar.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(
        value = """
            SELECT * 
            FROM category 
            WHERE EXISTS (
                SELECT 1 
                FROM car 
                WHERE car.location_id = ?1 
                AND category.id = car.category_id
            )
        """, 
        nativeQuery = true
    )
    List<CategoryEntity> findAllCategoriesByLocation(Long locationId);

    @Query(
        value = """
            SELECT * 
            FROM category 
            WHERE EXISTS (
                SELECT 1 
                FROM car 
                WHERE car.location_id = ?1 
                AND car.is_available = 1 
                AND category.id = car.category_id
            )
        """, 
        nativeQuery = true
    )
    List<CategoryEntity> findAvailableCategoriesByLocation(Long locationId);

    @Query(
        value = """
            SELECT price 
            FROM category 
            WHERE category.id = ?1
        """, 
        nativeQuery = true
    )
    BigDecimal getCategoryPrice(Long categoryId);
}
