package com.grupo4.api_alucar.repository;

import com.grupo4.api_alucar.model.ImageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
