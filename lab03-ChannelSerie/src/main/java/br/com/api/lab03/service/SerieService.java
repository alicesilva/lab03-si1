package br.com.api.lab03.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.api.lab03.model.Serie;
import br.com.api.lab03.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	SerieRepository serieRepository;

	public Serie registerSerie(Serie serie) {
		return serieRepository.save(serie);
	}

}
