package com.grupo4.api_alucar.model;

import java.util.Set;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "location")
public class LocationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Insert a value")
    private String city;

    //@NotBlank(message = "Insert a value")
    private String state;

    //@NotBlank(message = "Insert a value")
    private String country;

    @OneToMany
    @JoinColumn(name = "location_id") 
    private Set<ProductEntity> productList;
}
