package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public Dentista salvaDentista(@RequestBody Dentista dentista) throws SQLException{
        return dentistaService.salvar(dentista);
    }
    @GetMapping
    public List<Dentista> buscarTodos() throws SQLException{
        return dentistaService.buscarTodos();
    }
    @RequestMapping(value = "/buscaId")
    public Dentista buscarPorId(@RequestParam("id") int id) throws SQLException{
        Dentista dentista = dentistaService.buscarPorId(id).get();
        return dentista == null ? new Dentista() : dentista;
    }
    @PutMapping
    public void alterar(@RequestBody Dentista dentista) throws SQLException {
        System.out.println();
        dentistaService.alterar(dentista);
    }/*
    public ResponseEntity<Dentista> alterar(@RequestBody Dentista dentista) throws SQLException{
        ResponseEntity responseEntity = null;
        if(dentistaService.buscarPorId(dentista.getId()) == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }*/
    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException {
        dentistaService.excluir(id);
    }/*
    public ResponseEntity<Dentista> excluir(@PathVariable Integer id) throws SQLException{
        ResponseEntity responseEntity = null;
        if (dentistaService.buscarPorId(id) == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }*/
}