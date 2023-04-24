package com.group4.alucar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarRequestDto {
    private Long id;
    private String brand;
    private String model;
    private Short modelYear;
    private String color;
    private String licensePlate;
    private Integer mileage;
    private Boolean isAvailable;
    private Long categoryId;
    private Long locationId;
}
