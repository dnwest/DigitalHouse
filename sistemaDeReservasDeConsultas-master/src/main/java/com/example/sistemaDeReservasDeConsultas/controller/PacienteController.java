package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.RessourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteServiceImpl service;

    public PacienteController(PacienteServiceImpl service) { this.service = service; }

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        return service.add(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscaPacienteId(@PathVariable Long id) throws RessourceNotFoundException {
        try{
        return service.getById(id);
    }catch
            (Exception e){
                throw new RessourceNotFoundException("Não foi encontrado um paciente com ID: " +id);
            }
        }
    @PutMapping("/atualizar")
    public void alterarPaciente(@RequestBody Paciente paciente) throws RessourceNotFoundException {
        try {
            service.update(paciente);
        } catch (Exception e) {
            throw new RessourceNotFoundException("Não foi alterar o paciente: " + paciente);
        }
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) throws RessourceNotFoundException {
        try {
        service.remove(id);
    }catch (Exception e){
            throw new RessourceNotFoundException("Não foi possível excluir o paciente ID:" +id);
        }
}
}
