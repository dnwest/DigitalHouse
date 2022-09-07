package com.example.sistemaDeReservasDeConsultas.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class DentistaEntity {

    private Long id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;

    public DentistaEntity(String nome, String sobrenome, String matriculaCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matriculaCadastro = matriculaCadastro;
    }

}
