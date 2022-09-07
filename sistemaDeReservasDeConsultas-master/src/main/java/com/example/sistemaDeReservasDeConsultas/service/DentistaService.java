package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.dao.IDao;
import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    IDao<Dentista> dentistaDaoH2;

    public Dentista salvar(Dentista dentista) throws SQLException{
        return dentistaDaoH2.salvar(dentista);
    }
    public List<Dentista> buscarTodos() throws SQLException {
        return dentistaDaoH2.buscarTodos();
    }
    public void alterar(Dentista dentista) throws SQLException {
        dentistaDaoH2.alterar(dentista);
    }
    public void excluir(int id) throws SQLException {
        dentistaDaoH2.excluir(id);
    }
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        return dentistaDaoH2.buscarPorId(id);
    }
}
