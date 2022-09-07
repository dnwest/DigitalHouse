package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) throws SQLException{
        return service.salvar(paciente);
    }
    @GetMapping
    public List<Paciente> buscarTodos() throws SQLException{
        return service.buscarTodos();
    }
    public ResponseEntity<List<Paciente>> buscarTodosPacientes() throws SQLException {
        ResponseEntity<List<Paciente>> re = null;
        if(service.buscarTodos() == null) {
            re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return re;
    }

    @RequestMapping(value = "/buscaId")
    public Paciente buscaPacienteId(@RequestParam("id") int id) throws SQLException{
        Paciente paciente = service.buscarPorId(id).get();
        return paciente == null ? new Paciente() : paciente;
    }

    @PatchMapping
    public void alterarPaciente(@RequestBody Paciente paciente) throws SQLException{
        System.out.println();
        service.alterar(paciente);
    }
    public ResponseEntity<Paciente> alterarPacienteResponse(@RequestBody Paciente paciente) throws SQLException{
        ResponseEntity re = null;
        if(service.buscarPorId(paciente.getId()) == null){
            re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return re;
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException{
        service.excluir(id);
    }
    public ResponseEntity<Paciente> excluir(@PathVariable Integer id) throws SQLException{
        ResponseEntity re = null;
        if (service.buscarPorId(id) == null){
            re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            re = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return re;
    }


}
