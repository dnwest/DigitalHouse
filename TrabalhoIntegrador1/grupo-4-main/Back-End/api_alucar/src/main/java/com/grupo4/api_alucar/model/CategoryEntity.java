package com.grupo4.api_alucar.model;

import java.util.Set;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "Insert a value")
    private String name;

    private String description;

   // @NotBlank(message = "Insert a value")
    private Double price;

    @OneToMany//(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Set<ProductEntity> productList;
}
