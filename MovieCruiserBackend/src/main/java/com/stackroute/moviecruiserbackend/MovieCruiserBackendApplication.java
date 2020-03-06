package com.stackroute.moviecruiserbackend;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.stackroute.moviecruiserbackend.filter.JwtFilter;

@SpringBootApplication
public class MovieCruiserBackendApplication {
	
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean filterBean= new FilterRegistrationBean();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/*");
		return filterBean;		
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserBackendApplication.class, args);
	}
	
	
}
