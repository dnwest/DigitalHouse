package com.group4.alucar.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "category")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name field cannot be blank.")
    @Size(min = 1, max = 50, message = "The name field must be a string with a minimum length of 1 and a maximum length of 50.")
    private String name;
    
    @NotBlank(message = "The description field cannot be blank.")
    @Size(min = 10, max = 250, message = "The description field must be a string with a minimum length of 10 and a maximum length of 250.")
    private String description;
    
    @NotNull(message = "The price field cannot be null.")
    @DecimalMin(value = "50.00", inclusive = true, message = "The price field must be a number between 50.00 and 999.99.")
    @Digits(integer = 3, fraction = 2, message = "The price field must be a number between 50.00 and 999.99.")
    private BigDecimal price;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<ImageEntity> images;

    @ManyToMany
    @JoinTable(name = "category_feature", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private List<FeatureEntity> features;
}
