package com.group4.alucar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.location_id = ?1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAllCarsByLocation(Long locationId);

    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.location_id = ?1
            AND car.is_available = 1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAvailableCarsByLocation(Long locationId);

    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.category_id = ?1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAllCarsByCategory(Long categoryId);

    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.category_id = ?1
            AND car.is_available = 1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAvailableCarsByCategory(Long categoryId);

    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.category_id = ?1 
            AND car.location_id = ?2
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAllCarsByCategoryAndLocation(Long categoryId, Long locationId);

    @Query(
        value = """
            SELECT * 
            FROM car 
            WHERE car.category_id = ?1 
            AND car.location_id = ?2 
            AND car.is_available = 1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAvailableCarsByCategoryAndLocation(Long categoryId, Long locationId);

    @Query(
        value = """
            SELECT car.id AS car_id, car.* 
            FROM car 
            INNER JOIN category 
            ON car.category_id = category.id 
            WHERE car.location_id = ?1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAllCarsAndCategoriesByLocation(Long locationId);

    @Query(
        value = """
            SELECT car.id AS car_id, car.* 
            FROM car 
            INNER JOIN category 
            ON car.category_id = category.id 
            WHERE car.location_id = ?1 
            AND car.is_available = 1
        """, 
        nativeQuery = true
    )
    List<CarEntity> findAvailableCarsAndCategoriesByLocation(Long locationId);
}
