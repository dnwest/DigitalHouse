package com.example.exemploException.repository;

import com.example.exemploException.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel,Integer> {

}
