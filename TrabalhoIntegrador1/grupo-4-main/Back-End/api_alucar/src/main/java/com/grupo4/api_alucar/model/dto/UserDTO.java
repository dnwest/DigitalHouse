package com.grupo4.api_alucar.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo4.api_alucar.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    //private String password;

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        //this.password = user.getPassword();
    }
}
