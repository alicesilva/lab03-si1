package br.com.api.lab03.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.lab03.model.Serie;
import br.com.api.lab03.model.UserSystem;
import br.com.api.lab03.service.SerieService;
import br.com.api.lab03.service.UserSystemService;

@RestController
@RequestMapping("/admin")
public class UserSystemController {
	
	@Autowired
	UserSystemService usuarioService;
	
	@Autowired
	SerieService serieService;
	
	
	@RequestMapping(method=RequestMethod.GET, value = "/searchUser/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> buscarUsers(@PathVariable("password") String password){
		UserSystem foundUser = usuarioService.searchUserToPassword(password);
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerSerieToProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerSerieToProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem foundUser = usuarioService.registerSerieToProfile(id, registeredSerie);
		return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerSerieToWatchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerSerieToWatchlist(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem user = usuarioService.registerSerieToWatchlist(id, registeredSerie);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/addNoteEvaluationToSerie/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> addNoteEvaluationToSerie(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem user = usuarioService.addNoteEvaluationToSerie(id, serie);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/addLastChapterWatchedTheSerie/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> addLastChapterWatchedTheSerie(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem user = usuarioService.addLastChapterWatchedTheSerie(id, serie);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.POST, value = "/removeSeriesFromProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> removeSeriesFromProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem user = usuarioService.removeSeriesFromProfile(id, serie);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerWatchlistSerieOnProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerWatchlistSerieOnProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem usuario = usuarioService.registerWatchlistSerieOnProfile(id, registeredSerie);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	

}
