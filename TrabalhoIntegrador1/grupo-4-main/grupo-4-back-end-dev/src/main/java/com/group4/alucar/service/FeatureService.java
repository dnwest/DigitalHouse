package com.group4.alucar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.model.FeatureEntity;
import com.group4.alucar.repository.FeatureRepository;

@Service
public class FeatureService {
    @Autowired
    FeatureRepository repository;

    public FeatureEntity save(FeatureEntity feature) {
        return repository.save(feature);
    }

    public List<FeatureEntity> getAll() {
        return repository.findAll();
    }

    public Optional<FeatureEntity> getById(Long id) {
        return repository.findById(id);
    }

    public FeatureEntity update(FeatureEntity feature) {
        return repository.save(feature);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
