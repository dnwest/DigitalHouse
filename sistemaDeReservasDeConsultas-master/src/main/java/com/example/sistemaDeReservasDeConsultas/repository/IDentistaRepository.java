package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.entity.DentistaEntity;

public interface IDentistaRepository extends JpaRepository<DentistaEntity, Long> { }