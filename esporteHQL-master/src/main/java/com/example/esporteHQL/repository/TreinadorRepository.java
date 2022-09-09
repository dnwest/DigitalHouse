package com.example.esporteHQL.repository;

import com.example.esporteHQL.model.TreinadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TreinadorRepository extends JpaRepository<TreinadorModel,Long> {

    @Query("select treinador t from Treinador where t.idade = ?1")
    Optional<TreinadorModel> findTreinadorByIdade(Integer idade);
}
