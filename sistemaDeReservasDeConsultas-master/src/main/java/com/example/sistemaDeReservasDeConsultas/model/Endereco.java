package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity @Table(name = "enderecos")
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String pais;

}