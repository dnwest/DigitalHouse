package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.dao.IDao;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    IDao<Paciente> pacienteDaoH2;

    public Paciente salvar(Paciente paciente) throws SQLException{
        return pacienteDaoH2.salvar(paciente);
    }

    public List<Paciente> buscarTodos() throws SQLException{
        return pacienteDaoH2.buscarTodos();
    }

    public void alterar(Paciente paciente) throws SQLException{
        pacienteDaoH2.alterar(paciente);
    }

    public Optional<Paciente> buscarPorId(int id) throws SQLException{
        return pacienteDaoH2.buscarPorId(id);
    }

    public void excluir(int id) throws SQLException{
        pacienteDaoH2.excluir(id);
    }
}
