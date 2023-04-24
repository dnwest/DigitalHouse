package com.group4.alucar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
