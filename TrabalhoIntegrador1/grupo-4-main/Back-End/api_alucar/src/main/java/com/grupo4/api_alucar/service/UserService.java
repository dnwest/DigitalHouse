package com.grupo4.api_alucar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo4.api_alucar.model.UserEntity;
import com.grupo4.api_alucar.model.dto.UserDTO;
import com.grupo4.api_alucar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDTO save(UserEntity user){
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.convertValue(user, UserDTO.class);

        repository.save(user);

        return userDTO;
    }

    public List<UserDTO> getAll(){
        ObjectMapper mapper = new ObjectMapper();

        List<UserEntity> userEntityList = repository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for ( UserEntity user: userEntityList) {
            userDTOList.add(new UserDTO(user));
        }

        return userDTOList;
    }

    public UserDTO getById(Long id){
        ObjectMapper mapper = new ObjectMapper();

        Optional<UserEntity> userEntity = repository.findById(id);
        UserDTO UserDTO = mapper.convertValue(userEntity.get(),UserDTO.class);

        return UserDTO;
    }

    public UserDTO update(UserEntity user){
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.convertValue(user, UserDTO.class);

        repository.save(user);

        return userDTO;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
