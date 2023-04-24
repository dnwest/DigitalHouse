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
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The description field cannot be blank.")
    @Size(min = 2, max = 50, message = "The description field must be a string with a minimum length of 2 and a maximum length of 50.")
    private String description;
    
    @NotBlank(message = "The url field cannot be blank.")
    @Size(min = 2, max = 250, message = "The url field must be a string with a minimum length of 2 and a maximum length of 250.")
    private String url;
}
