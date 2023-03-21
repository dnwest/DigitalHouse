package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.LocationEntity;
import com.grupo4.api_alucar.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    LocationRepository repository;

    public LocationEntity save(LocationEntity location){
        repository.save(location);
        return location;
    }

    public List<LocationEntity> getAll(){
        return repository.findAll();
    }

    public Optional<LocationEntity> getById(Long id){
        return repository.findById(id);
    }

    public LocationEntity update(LocationEntity location){
        repository.save(location);
        return location;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
