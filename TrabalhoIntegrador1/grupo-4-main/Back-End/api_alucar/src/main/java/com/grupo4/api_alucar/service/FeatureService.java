package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.FeatureEntity;
import com.grupo4.api_alucar.repository.FeatureRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
    @Autowired
    FeatureRepository repository;

    public FeatureEntity save(FeatureEntity feature){
        repository.save(feature);
        return feature;
    }

    public List<FeatureEntity> getAll(){
        return repository.findAll();
    }

    public Optional<FeatureEntity> getById(Long id){
        return repository.findById(id);
    }

    public FeatureEntity update(FeatureEntity feature){
        repository.save(feature);
        return feature;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
