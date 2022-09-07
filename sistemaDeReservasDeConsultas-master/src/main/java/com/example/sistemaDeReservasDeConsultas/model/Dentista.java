package com.example.sistemaDeReservasDeConsultas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dentista {
    private int id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;
}
