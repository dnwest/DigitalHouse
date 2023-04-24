package com.group4.alucar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.alucar.dto.CarRequestDto;
import com.group4.alucar.model.CarEntity;
import com.group4.alucar.repository.CarRepository;
import com.group4.alucar.repository.CategoryRepository;
import com.group4.alucar.repository.LocationRepository;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LocationRepository locationRepository;
   
    public CarEntity save(CarRequestDto car) {
        CarEntity carEntity = CarEntity.builder()
            .brand(car.getBrand())
            .model(car.getModel())
            .modelYear(car.getModelYear())
            .color(car.getColor())
            .licensePlate(car.getLicensePlate())
            .mileage(car.getMileage())
            .isAvailable(car.getIsAvailable())
            .category(categoryRepository.getReferenceById(car.getCategoryId()))
            .location(locationRepository.getReferenceById(car.getLocationId()))
            .build();

        return carRepository.save(carEntity);
    }

    public List<CarEntity> getAll() {
        return carRepository.findAll();
    }

    public Optional<CarEntity> getById(Long id) {
        return carRepository.findById(id);
    }

    public CarEntity update(CarRequestDto car) {
        CarEntity carEntity = CarEntity.builder()
        .id(car.getId())
        .brand(car.getBrand())
        .model(car.getModel())
        .modelYear(car.getModelYear())
        .color(car.getColor())
        .licensePlate(car.getLicensePlate())
        .mileage(car.getMileage())
        .isAvailable(car.getIsAvailable())
        .category(categoryRepository.getReferenceById(car.getCategoryId()))
        .location(locationRepository.getReferenceById(car.getLocationId()))
        .build();

    return carRepository.save(carEntity);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public List<CarEntity> getAllCarsByLocation(Long locationId) {
        return carRepository.findAllCarsByLocation(locationId);
    }

    public List<CarEntity> getAvailableCarsByLocation(Long locationId) {
        return carRepository.findAvailableCarsByLocation(locationId);
    }

    public List<CarEntity> getAllCarsByCategory(Long categoryId) {
        return carRepository.findAllCarsByCategory(categoryId);
    }

    public List<CarEntity> getAvailableCarsByCategory(Long categoryId) {
        return carRepository.findAvailableCarsByCategory(categoryId);
    }

    public List<CarEntity> getAllCarsByCategoryAndLocation(Long categoryId, Long locationId) {
        return carRepository.findAllCarsByCategoryAndLocation(categoryId, locationId);
    }

    public List<CarEntity> getAvailableCarsByCategoryAndLocation(Long categoryId, Long locationId) {
        return carRepository.findAvailableCarsByCategoryAndLocation(categoryId, locationId);
    }
}
