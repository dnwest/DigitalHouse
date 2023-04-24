package com.group4.alucar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.model.ClientEntity;
import com.group4.alucar.model.RoleEntity;
import com.group4.alucar.repository.ClientRepository;
import com.group4.alucar.repository.RoleRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    public ClientEntity save(ClientEntity clientEntity) {
        clientEntity.getUser().setRoles(new ArrayList<RoleEntity>(Arrays.asList(roleRepository.getReferenceById(2L))));
        return clientRepository.save(clientEntity);
    }

    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> getById(Long id) {
        return clientRepository.findById(id);
    }

    public ClientEntity update(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientEntity getClientByUsername(String username) {
        return clientRepository.findClientByUsername(username);
    }
}
