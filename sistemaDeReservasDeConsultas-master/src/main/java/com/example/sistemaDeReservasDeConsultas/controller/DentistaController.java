package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.service.DentistaServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private final DentistaServiceImpl service;

    public DentistaController(DentistaServiceImpl service) { this.service = service; }

    @PostMapping
    public Dentista cadastrarDentista(@RequestBody Dentista dentista) {
        return service.add(dentista);
    }

    @GetMapping
    public List<Dentista> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Dentista> buscaDentistaId(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/atualizar")
    public void alterarDentista(@RequestBody Dentista dentista) {
        service.update(dentista);
    }

    @DeleteMapping("/{id}")
    public void excluirDentista(@PathVariable Long id) {
        service.remove(id);
    }
}
