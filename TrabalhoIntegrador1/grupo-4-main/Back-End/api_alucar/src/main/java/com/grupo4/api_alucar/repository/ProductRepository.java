package com.grupo4.api_alucar.repository;

import com.grupo4.api_alucar.model.ProductEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT * FROM product Where location_id = ?1", nativeQuery = true)
    List<ProductEntity> findProductsByLocation(Long locationId);

    @Query(value = "SELECT * FROM product Where category_id = ?1", nativeQuery = true)
    List<ProductEntity> findProductsByCategory(Long categoryId);
}
