package com.group4.alucar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.model.ImageEntity;
import com.group4.alucar.repository.ImageRepository;

@Service
public class ImageService {
    @Autowired
    ImageRepository repository;

    public ImageEntity save(ImageEntity image) {
        return repository.save(image);
    }

    public List<ImageEntity> getAll() {
        return repository.findAll();
    }

    public Optional<ImageEntity> getById(Long id) {
        return repository.findById(id);
    }

    public ImageEntity update(ImageEntity image) {
        return repository.save(image);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
