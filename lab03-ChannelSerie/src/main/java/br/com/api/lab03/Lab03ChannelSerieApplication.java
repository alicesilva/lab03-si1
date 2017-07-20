package br.com.api.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.api.lab03.controller.TokenFilter;

@SpringBootApplication
public class Lab03ChannelSerieApplication {
	
	@Bean
	public FilterRegistrationBean filtroJwt(){
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/admin/*");
		return frb;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab03ChannelSerieApplication.class, args);
	}
}
