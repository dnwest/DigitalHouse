package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.entity.PacienteEntity;

public interface IPacienteRepository extends JpaRepository<PacienteEntity, Long> { }
