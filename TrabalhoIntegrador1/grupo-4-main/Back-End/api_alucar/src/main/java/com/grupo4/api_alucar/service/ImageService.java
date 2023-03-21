package com.grupo4.api_alucar.service;

import com.grupo4.api_alucar.model.ImageEntity;
import com.grupo4.api_alucar.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository repository;

    public ImageEntity save(ImageEntity image){
        repository.save(image);
        return image;
    }

    public List<ImageEntity> getAll(){
        return repository.findAll();
    }

    public Optional<ImageEntity> getById(Long id){
        return repository.findById(id);
    }

    public ImageEntity update(ImageEntity image){
        repository.save(image);
        return image;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
