package com.group4.alucar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.model.LocationEntity;
import com.group4.alucar.repository.LocationRepository;

@Service
public class LocationService {
    @Autowired
    LocationRepository repository;

    public LocationEntity save(LocationEntity location) {
        return repository.save(location);
    }

    public List<LocationEntity> getAll() {
        return repository.findAll();
    }

    public Optional<LocationEntity> getById(Long id) {
        return repository.findById(id);
    }

    public LocationEntity update(LocationEntity location) {
        return repository.save(location);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
