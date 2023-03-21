package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.ClientEntity;
import com.grupo4.api_alucar.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    public ClientEntity save(ClientEntity clientEntity){
        repository.save(clientEntity);
        return clientEntity;
    }

    public List<ClientEntity> getAll(){
        return repository.findAll();
    }

    public Optional<ClientEntity> getById(Long id){
        return repository.findById(id);
    }

    public ClientEntity update(ClientEntity clientEntity){
        repository.save(clientEntity);
        return clientEntity;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
