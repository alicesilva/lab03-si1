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
import io.undertow.attribute.RequestMethodAttribute;

@RestController
@RequestMapping("/admin")
public class UserSystemController {
	
	@Autowired
	UserSystemService userService;
	
	@Autowired
	SerieService serieService;
	
	
	@RequestMapping(method=RequestMethod.POST, value = "/searchUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> buscarUsers(@RequestBody UserSystem user){
		UserSystem foundUser = userService.searchUserToEmail(user.getEmail());
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerSerieToProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerSerieToProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem userSystem = userService.registerSerieToProfile(id, registeredSerie);
		return new ResponseEntity<>(userSystem, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerSerieToWatchlist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerSerieToWatchlist(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem userSystem = userService.registerSerieToWatchlist(id, registeredSerie);
		return new ResponseEntity<>(userSystem, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/addNoteEvaluationToSerie/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> addNoteEvaluationToSerie(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem userSystem = userService.addNoteEvaluationToSerie(id, serie);
		
		return new ResponseEntity<>(userSystem, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/addLastChapterWatchedTheSerie/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> addLastChapterWatchedTheSerie(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem userSystem = userService.addLastChapterWatchedTheSerie(id, serie);
		
		return new ResponseEntity<>(userSystem, HttpStatus.OK);
	}
	

	@RequestMapping(method=RequestMethod.POST, value = "/removeSeriesFromProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> removeSeriesFromProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		UserSystem userSystem = userService.removeSeriesFromProfile(id, serie);
		
		return new ResponseEntity<>(userSystem, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value = "/registerWatchlistSerieOnProfile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> registerWatchlistSerieOnProfile(@PathVariable("id") Integer id, @RequestBody Serie serie){
		Serie registeredSerie = serieService.registerSerie(serie);
		
		UserSystem userSystem = userService.registerWatchlistSerieOnProfile(id, registeredSerie);
		return new ResponseEntity<>(userSystem, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/getEvaluationNote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> getEvaluationNote(@PathVariable("id") Integer id, @RequestBody Serie serie){
		
		Serie serieFound = userService.getEvaluationNote(id, serie);
		return new ResponseEntity<>(serieFound, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/getlastWatchedChapter/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> getlastWatchedChapter(@PathVariable("id") Integer id, @RequestBody Serie serie){
		
		Serie serieFound = userService.getlastWatchedChapter(id, serie);
		return new ResponseEntity<>(serieFound, HttpStatus.CREATED);
	}
	
	
	
	

}
