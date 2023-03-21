package com.grupo4.api_alucar;

import com.grupo4.api_alucar.model.RoleEntity;
import com.grupo4.api_alucar.model.UserEntity;
import com.grupo4.api_alucar.repository.RoleRepository;
import com.grupo4.api_alucar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // ROLE ENTITIES
        RoleEntity admin = new RoleEntity();
        admin.setRole("ADMIN");
        RoleEntity user = new RoleEntity();
        user.setRole("USER");

        roleRepository.save(admin);
        roleRepository.save(user);

        // USERS ROOT
          //ADMIN
        UserEntity user1 = new UserEntity();
        user1.setUsername("admin@email.com");
        user1.setPassword("admin123");

        List<RoleEntity> rolesUser1 = new ArrayList<>();
        rolesUser1.add(admin);

        user1.setRoles(rolesUser1);

        userRepository.save(user1);

          //USER
        UserEntity user2 = new UserEntity();
        user2.setUsername("user@email.com");
        user2.setPassword("user123");

        List<RoleEntity> rolesUser2 = new ArrayList<>();
        rolesUser2.add(user);

        user2.setRoles(rolesUser2);

        userRepository.save(user2);

    }
}
