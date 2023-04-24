package com.group4.alucar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "location")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The country field cannot be null.")
    @Size(min = 2, max = 50, message = "The country field must be a string with a minimum length of 2 and a maximum length of 50.")
    @Pattern(regexp = "^(?:[a-zA-Z\\u00C0-\\u00FF]+(?:\\. |-| |'))*[a-zA-Z\\u00C0-\\u00FF]+$", message = "The country field contains invalid characters.")
    private String country;
    
    @NotNull(message = "The state field cannot be null.")
    @Size(min = 2, max = 50, message = "The state field must be a string with a minimum length of 2 and a maximum length of 50.")
    @Pattern(regexp = "^(?:[a-zA-Z\\u00C0-\\u00FF]+(?:\\. |-| |'))*[a-zA-Z\\u00C0-\\u00FF]+$", message = "The state field contains invalid characters.")
    private String state;
    
    @NotNull(message = "The city field cannot be null.")
    @Size(min = 2, max = 50, message = "The city field must be a string with a minimum length of 2 and a maximum length of 50.")
    @Pattern(regexp = "^(?:[a-zA-Z\\u00C0-\\u00FF]+(?:\\. |-| |'))*[a-zA-Z\\u00C0-\\u00FF]+$", message = "The city field contains invalid characters.")
    private String city;
}
