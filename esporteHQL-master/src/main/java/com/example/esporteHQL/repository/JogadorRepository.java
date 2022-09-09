package com.example.esporteHQL.repository;

import com.example.esporteHQL.model.JogadorModel;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<JogadorModel,Long> {

    @Query("select jogador j from Jogador where j.nome = ?1")
    Optional<JogadorModel> findJogadorByName(String name);//?1 vai pegar exatamente o nome
    //que eu vou passar no meu findJogadorByName
}
