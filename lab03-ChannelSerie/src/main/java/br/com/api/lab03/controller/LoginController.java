package br.com.api.lab03.controller;


import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.lab03.model.UserSystem;
import br.com.api.lab03.service.UserSystemService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	private UserSystemService userService;
	
	@RequestMapping(value="/authenticate", consumes=MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody  UserSystem userSystem) throws ServletException{
		
		if (userSystem.getEmail() ==null || userSystem.getPassword()==null){
			throw new ServletException("Email and password required!");
		}
		
		UserSystem authenticatedUser = userService.searchUserToEmail(userSystem.getEmail());
		
		if (authenticatedUser==null){
			throw new ServletException("User not found!");
		}
		
		if (!authenticatedUser.getPassword().equals(userSystem.getPassword())){
			throw new ServletException("Invalid email or password.");
		}
		
		String token=  Jwts.builder()
				.setSubject(authenticatedUser.getName())
				.signWith(SignatureAlgorithm.HS512, "banana")
				.compact();
		
		
		return	new LoginResponse(token);
	}		
	
	private class LoginResponse{
		public String token;
		 
		public LoginResponse(String token ){
			this.token=token; 
		 }
		
	}
}
