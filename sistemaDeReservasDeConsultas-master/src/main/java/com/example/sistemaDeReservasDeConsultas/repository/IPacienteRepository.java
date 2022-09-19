package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> { }
