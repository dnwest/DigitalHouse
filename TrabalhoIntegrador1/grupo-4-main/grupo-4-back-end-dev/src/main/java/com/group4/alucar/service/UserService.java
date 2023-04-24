package com.group4.alucar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group4.alucar.dto.UserDto;
import com.group4.alucar.model.UserEntity;
import com.group4.alucar.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDto save(UserEntity user) {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = mapper.convertValue(user, UserDto.class);

        repository.save(user);

        return userDto;
    }

    public List<UserDto> getAll() {
        List<UserEntity> userEntityList = repository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity user : userEntityList) {
            userDtoList.add(new UserDto(user));
        }

        return userDtoList;
    }

    public UserDto getById(Long id) {
        ObjectMapper mapper = new ObjectMapper();

        Optional<UserEntity> userEntity = repository.findById(id);
        UserDto userDto = mapper.convertValue(userEntity.get(), UserDto.class);

        return userDto;
    }

    public UserDto update(UserEntity user) {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = mapper.convertValue(user, UserDto.class);

        repository.save(user);

        return userDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
