package br.com.api.lab03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.lab03.model.Serie;
import br.com.api.lab03.service.SerieService;

@RestController
public class SerieController {
	
	@Autowired
	SerieService serieService;
	
	
	public Serie registerSerie(Serie serie){
		Serie serieCadastrado = serieService.registerSerie(serie);
		
		return serieCadastrado;
	}

}
