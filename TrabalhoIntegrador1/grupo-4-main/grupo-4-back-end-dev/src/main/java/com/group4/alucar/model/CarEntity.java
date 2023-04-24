package com.group4.alucar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The brand field cannot be blank.")
    @Size(min = 2, max = 50, message = "The brand field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String brand;
    
    @NotBlank(message = "The model field cannot be blank.")
    @Size(min = 2, max = 50, message = "The model field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String model;
    
    @NotNull(message = "The modelYear field cannot be null.")
    @Positive(message = "The modelYear field must be a positive number.")
    private Short modelYear;
    
    @NotBlank(message = "The color field cannot be blank.")
    @Size(min = 2, max = 50, message = "The color field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String color;

    @Pattern(regexp = "^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "The licensePlate field must be a string in ABC1D23 format.")
    private String licensePlate;
    
    @NotNull(message = "The mileage field cannot be null.")
    @PositiveOrZero(message = "The mileage field must be a positive integer or 0.")
    private Integer mileage;
    
    @NotNull(message = "The isAvailable field cannot be null.")
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private LocationEntity location;
}
