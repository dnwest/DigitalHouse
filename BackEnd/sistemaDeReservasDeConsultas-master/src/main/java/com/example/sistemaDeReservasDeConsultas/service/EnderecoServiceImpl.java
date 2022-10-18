package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.repository.IEnderecoRepository;

@Service
public class EnderecoServiceImpl implements IService<Endereco> {

    private final IEnderecoRepository repository;

    public EnderecoServiceImpl(IEnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Endereco add(Endereco endereco) {
        if (endereco != null) {
            return repository.save(endereco);
        }
        return new Endereco();
    }

    @Override
    public List<Endereco> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Endereco> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalStateException("Repositório nulo");
        }
    }

    @Override
    public void update(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalStateException("Endereço nulo");
        } else if (endereco != null && repository.findById(endereco.getId()).isPresent()) {
            repository.saveAndFlush(endereco);
        }
    }

}
