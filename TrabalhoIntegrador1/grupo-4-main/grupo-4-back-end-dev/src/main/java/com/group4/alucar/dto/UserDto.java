package com.group4.alucar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group4.alucar.model.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    // private String password;

    public UserDto(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        // this.password = user.getPassword();
    }
}
