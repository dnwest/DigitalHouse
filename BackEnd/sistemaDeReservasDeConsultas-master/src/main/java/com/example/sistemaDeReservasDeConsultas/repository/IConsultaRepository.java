package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
}
