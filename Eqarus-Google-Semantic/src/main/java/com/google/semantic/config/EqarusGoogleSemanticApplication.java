package com.google.semantic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan({"com.google.semantic.controller","com.google.semantic.service.impl"
})
public class EqarusGoogleSemanticApplication {

	public static void main(String[] args) {
		SpringApplication.run(EqarusGoogleSemanticApplication.class, args);
	}

 }
