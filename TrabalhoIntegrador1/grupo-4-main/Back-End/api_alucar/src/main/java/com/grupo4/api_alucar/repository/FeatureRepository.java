package com.grupo4.api_alucar.repository;

import com.grupo4.api_alucar.model.FeatureEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
}
