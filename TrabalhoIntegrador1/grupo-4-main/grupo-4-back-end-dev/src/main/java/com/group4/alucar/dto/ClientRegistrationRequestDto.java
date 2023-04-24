package com.group4.alucar.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.alucar.model.ClientEntity;
import com.group4.alucar.model.UserEntity;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRegistrationRequestDto {
    private Long id;
    private String fullName;
    private String nationalId; // CPF
    private String phoneNumber;
    private String drivingLicenseNumber; // CNH
    private LocalDate drivingLicenseValidity;
    private LocalDate birthdate;
    private String username;
    private String password;

    public static ClientEntity toClientEntity(ClientRegistrationRequestDto client) {
        UserEntity user = new UserEntity();
        user.setUsername(client.username);
        user.setPassword(client.password);
        
        return ClientEntity.builder()
            .fullName(client.fullName)
            .nationalId(client.nationalId)
            .phoneNumber(client.phoneNumber)
            .drivingLicenseNumber(client.drivingLicenseNumber)
            .drivingLicenseValidity(client.drivingLicenseValidity)
            .birthdate(client.birthdate)
            .user(user)
            .build();
    }
}
