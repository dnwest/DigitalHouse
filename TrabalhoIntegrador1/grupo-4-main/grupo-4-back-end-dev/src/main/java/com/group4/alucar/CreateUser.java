// package com.group4.alucar;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.context.annotation.Configuration;

// import com.group4.alucar.model.RoleEntity;
// import com.group4.alucar.model.UserEntity;
// import com.group4.alucar.repository.RoleRepository;
// import com.group4.alucar.repository.UserRepository;

// @Configuration
// public class CreateUser implements ApplicationRunner {

//     @Autowired
//     RoleRepository roleRepository;

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     public void run(ApplicationArguments args) throws Exception {

//         // ROLE ENTITIES
//         RoleEntity adminRole = new RoleEntity();
//         adminRole.setRole("ROLE_ADMIN");

//         RoleEntity userRole = new RoleEntity();
//         userRole.setRole("ROLE_USER");

//         roleRepository.save(adminRole);
//         roleRepository.save(userRole);

//         // ADMIN
//         UserEntity user1 = new UserEntity();
//         user1.setUsername("admin@email.com");
//         user1.setPassword("Admin#123");

//         List<RoleEntity> rolesUser1 = new ArrayList<>();
//         rolesUser1.add(adminRole);

//         user1.setRoles(rolesUser1);

//         userRepository.save(user1);

//         // USER
//         UserEntity user2 = new UserEntity();
//         user2.setUsername("user@email.com");
//         user2.setPassword("User#123");

//         List<RoleEntity> rolesUser2 = new ArrayList<>();
//         rolesUser2.add(userRole);

//         user2.setRoles(rolesUser2);

//         userRepository.save(user2);

//         // PRINT
//         System.out.println("Admin: " + user1);
//         System.out.println("User: " + user2);
//     }
// }
