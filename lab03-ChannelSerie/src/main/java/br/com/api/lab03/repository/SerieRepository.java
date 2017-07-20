package br.com.api.lab03.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.api.lab03.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Integer>{

}
