package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @Data
@Entity @Table(name = "dentistas")
public class Dentista {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;

    @OneToMany(mappedBy = "dentista", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Consulta> consultas = new ArrayList<>();
}
