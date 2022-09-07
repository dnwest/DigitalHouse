package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.entity.PacienteEntity;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;

@Service
public class PacienteServiceImpl implements IPacienteService<PacienteEntity> {

    private final IPacienteRepository repository;

    public PacienteServiceImpl(IPacienteRepository repository) { this.repository = repository; }

    @Override
    public PacienteEntity add(PacienteEntity paciente) {
        if ( paciente != null) { return (PacienteEntity) repository.save(paciente); }
        return new PacienteEntity();
    }

    @Override
    public List<PacienteEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PacienteEntity> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else { throw new IllegalStateException("Repositório nulo"); }
        
    }

    @Override
    public void update(PacienteEntity paciente) {
        if (paciente == null) { throw new IllegalStateException("Paciente nulo"); }
        Long id = paciente.getId();
        if (getById(id) == null) { throw new IllegalStateException("Paciente nulo"); }
        repository.deleteById(id);
    }
    
}