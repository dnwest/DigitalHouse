package com.example.professorHibernate.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Professor") // definir o nome da tabela
public class ProfessorModel {

    @Id
    @SequenceGenerator(name = "professor_sequence",sequenceName = "professor_sequence",allocationSize = 1)
    //vai criar e controlar toda a criação dos nossos ids
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "professor_sequence")
    //será a forma que essa sequência será gerada
    private Long id;
    private String nome;
    private String titulo;

    @OneToMany(mappedBy = "professorModel",fetch =  FetchType.LAZY)
    //o fetch indica que quando eu trouxer os professores não trazer os alunos junto
    //porque teria um tráfego grande de dados
    private Set<AlunoModel> alunos = new HashSet<>();

    public ProfessorModel(){

    }

    public ProfessorModel(String nome, String titulo) {
        this.nome = nome;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
