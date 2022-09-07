package com.example.sistemaDeReservasDeConsultas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private int id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String rg;
    private Date dataDeAlta;
}
