package com.group4.alucar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.FeatureEntity;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
}
