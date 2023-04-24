package com.group4.alucar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "feature")
public class FeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The description field cannot be blank.")
    @Size(min = 2, max = 50, message = "The description field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String description;
    
    @NotBlank(message = "The icon field cannot be blank.")
    @Size(min = 2, max = 50, message = "The icon field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String icon;
}
