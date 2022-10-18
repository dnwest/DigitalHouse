package com.example.hibernate1.model;

import java.io.Serializable;
import java.util.Objects;

public class PessoaKey implements Serializable {

    private Integer cpf;
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


    //essas duas implementações ele vai verificar e trazer a chave para nós
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o== null || getClass() != o.getClass()) return false;
        PessoaKey pessoaKey = (PessoaKey) o;
        return Objects.equals(cpf,pessoaKey.cpf) && Objects.equals(identidade,pessoaKey.identidade);

    }

    @Override
    public int hashCode(){
        return Objects.hash(cpf,identidade);
    }
}
