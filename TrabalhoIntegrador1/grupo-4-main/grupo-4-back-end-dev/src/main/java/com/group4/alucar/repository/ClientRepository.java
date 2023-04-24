package com.group4.alucar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group4.alucar.model.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Query(
        value = """
            SELECT * 
            FROM client 
            WHERE username = ?1 
        """, 
        nativeQuery = true
    )
    ClientEntity findClientByUsername(String username);
}
