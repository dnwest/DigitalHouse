package com.grupo4.api_alucar.model;

import java.util.Set;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Insert a value")
    private String model;

    //@NotBlank(message = "Insert a value")
    private String brand;

    private String licensePlate;

    private Long mileage;

    private Boolean available;

    // private CategoryEntity category;  

    @OneToMany 
    @JoinColumn(name = "product_id")  
    private Set<ImageEntity> imageList;

    @ManyToMany
    @JoinTable(name = "product_feature",
    joinColumns = {@JoinColumn(name = "product_id")},
    inverseJoinColumns = {@JoinColumn(name = "feature_id")})  
    private Set<FeatureEntity> featureList;

    // private LocationEntity location;  
}
