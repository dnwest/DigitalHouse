package com.example.esporteHQL.service;

import com.example.esporteHQL.model.TreinadorModel;
import com.example.esporteHQL.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinadorService {

    private TreinadorRepository treinadorRepository;

    @Autowired
    public TreinadorService(TreinadorRepository treinadorRepository){
        this.treinadorRepository = treinadorRepository;
    }

    public List<TreinadorModel> buscarTodos(){
        return treinadorRepository.findAll();
    }

    public TreinadorModel buscaPorIdade(Integer idade){
        return treinadorRepository.findTreinadorByIdade(idade).get();
    }
}
