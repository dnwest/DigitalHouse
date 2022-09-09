package com.example.hibernate1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Pessoa")
@IdClass(value= PessoaKey.class)//neste caso está indicando que o id é uma classe que está passando a classe PessoaKey como valor
public class Pessoa {

    @Id
    private Integer cpf;
    @Id
    private Integer identidade;

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Integer getIdentidade() {
        return identidade;
    }

    public void setIdentidade(Integer identidade) {
        this.identidade = identidade;
    }
}
