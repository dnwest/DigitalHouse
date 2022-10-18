package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.service.EnderecoServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoServiceImpl service;

    public EnderecoController(EnderecoServiceImpl service) { this.service = service; }

    @PostMapping
    public Endereco cadastrarEndereco(@RequestBody Endereco endereco) {
        return service.add(endereco);
    }

    @GetMapping
    public List<Endereco> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Endereco> buscaEnderecoId(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/atualizar")
    public void alterarEndereco(@RequestBody Endereco endereco) {
        service.update(endereco);
    }

    @DeleteMapping("/{id}")
    public void excluirEndereco(@PathVariable Long id) {
        service.remove(id);
    }
}
