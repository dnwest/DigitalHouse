package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.repository.IConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IService<Consulta> {
    private final IConsultaRepository repository;

    public ConsultaServiceImpl(IConsultaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consulta add(Consulta consulta) {
        if (consulta != null) { return repository.save(consulta); }
        return new Consulta();
    }

    @Override
    public List<Consulta> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Consulta> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else {
            throw new IllegalStateException("Repositório nulo"); }
    }

    @Override
    public void update(Consulta consulta) {
        if (consulta == null) { throw new IllegalStateException("Consulta nulo"); }
        else if (consulta != null && repository.findById(consulta.getId()).isPresent()) {
            repository.saveAndFlush(consulta);
        }
    }


}


