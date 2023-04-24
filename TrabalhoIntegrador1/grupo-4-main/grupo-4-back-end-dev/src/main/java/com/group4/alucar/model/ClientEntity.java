package com.group4.alucar.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
@Table(name = "client")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The fullName field cannot be null.")
    @Size(min = 2, max = 100, message = "The fullName field must be a string with a minimum length of 2 and a maximum length of 100.")
    @Pattern(regexp = "^(?:[a-zA-Z\\u00C0-\\u00FF]+(?:\\. |-| |'))*[a-zA-Z\\u00C0-\\u00FF]+$", message = "The fullName field contains invalid characters.")
    private String fullName;
    
    @Pattern(regexp = "^\\d{11}$", message = "The nationalId field must be a numeric string in xxxxxxxxxxx format.")
    private String nationalId; // CPF
    
    @Pattern(regexp = "^\\d{11}$", message = "The phoneNumber field must be a numeric string in xxxxxxxxxxx format.")
    private String phoneNumber;
    
    @Pattern(regexp = "^\\d{11}$", message = "The drivingLicenseNumber field must be a numeric string in xxxxxxxxxxx format.")
    private String drivingLicenseNumber; // CNH
    
    @NotNull(message = "The drivingLicenseValidity field cannot be null.")
    @Future(message = "The drivingLicenseValidity field must be a date in the future.")
    private LocalDate drivingLicenseValidity;
    
    @NotNull(message = "The birthdate field cannot be null.")
    @Past(message = "The birthdate field must be a date in the past.")
    private LocalDate birthdate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @JsonIgnore
    private UserEntity user;
}
