package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.entity.DentistaEntity;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaServiceImpl implements IDentistaService<DentistaEntity> {

    private final IDentistaRepository repository;

    public DentistaServiceImpl(IDentistaRepository repository) { this.repository = repository; }

    @Override
    public DentistaEntity add(DentistaEntity dentista) {
        if ( dentista != null) { return (DentistaEntity) repository.save(dentista); }
        return new DentistaEntity();
    }

    @Override
    public List<DentistaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DentistaEntity> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else { throw new IllegalStateException("Repositório nulo"); }
        
    }

    @Override
    public void update(DentistaEntity dentista) {
        if (dentista == null) { throw new IllegalStateException("Dentista nulo"); }
        Long id = dentista.getId();
        if (getById(id) == null) { throw new IllegalStateException("Dentista nulo"); }
        repository.deleteById(id);
    }   
    
}
