package br.com.api.lab03.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.api.lab03.model.UserSystem;
import br.com.api.lab03.service.UserSystemService;

@Controller
public class RegisterController {
	
	@Autowired
	UserSystemService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST, value = "/userRegistration", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserSystem> cadastrar(@RequestBody UserSystem usuario){
		UserSystem usuarioCadastrado = usuarioService.userRegistration(usuario);
		
		return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
	}

}
