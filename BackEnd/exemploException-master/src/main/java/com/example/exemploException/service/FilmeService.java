package com.example.exemploException.service;

import com.example.exemploException.model.FilmeModel;
import com.example.exemploException.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }

    public FilmeModel salvar(FilmeModel filmeModel){
        return filmeRepository.save(filmeModel);
    }

    public List<FilmeModel> buscarTodos(){
        return filmeRepository.findAll();
    }

    public FilmeModel buscarId(Integer id){
        return filmeRepository.findById(id).get();
    }

    public FilmeModel atualizar(FilmeModel filmeModel){
        return filmeRepository.saveAndFlush(filmeModel);
    }

    public void deletar(Integer id){
        filmeRepository.deleteById(id);
    }
}
