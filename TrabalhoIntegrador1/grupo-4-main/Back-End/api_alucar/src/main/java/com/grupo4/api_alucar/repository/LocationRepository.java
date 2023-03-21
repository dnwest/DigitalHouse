package com.grupo4.api_alucar.repository;

import com.grupo4.api_alucar.model.LocationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
