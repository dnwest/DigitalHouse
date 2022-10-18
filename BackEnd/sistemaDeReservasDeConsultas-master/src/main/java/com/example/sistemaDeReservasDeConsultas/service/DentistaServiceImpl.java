package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaServiceImpl implements IService<Dentista> {

    private final IDentistaRepository repository;

    public DentistaServiceImpl(IDentistaRepository repository) { this.repository = repository; }

    @Override
    public Dentista add(Dentista dentista) {
        if ( dentista != null) { return repository.save(dentista); }
        return new Dentista();
    }

    @Override
    public List<Dentista> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Dentista> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else {
            throw new IllegalStateException("Repositório nulo"); }
    }

    @Override
    public void update(Dentista dentista) {
        if (dentista == null) { throw new IllegalStateException("Dentista nulo"); }
        else if (dentista != null && repository.findById(dentista.getId()).isPresent()) {
            repository.saveAndFlush(dentista);
        }
    }
    
}
