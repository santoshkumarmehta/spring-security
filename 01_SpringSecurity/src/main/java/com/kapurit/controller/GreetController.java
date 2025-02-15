package com.kapurit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetController {
	
	@GetMapping("/greet")
	public String greetMessage() {
	
		return "Welcome to Kapurit";
	}
	
	@GetMapping("/home")
	public String homeMessage() {
	
		return "Welcome to Kapurit Home Page";
	}
	
	@GetMapping("/pro")
	public String promotionalMessage() {
	
		return "Welcome to Kapurit, This is promotional Page";
	}

	@GetMapping("/contact")
	public String getContactDetails() {
		return "Contact Details : 9113863471";
	}
	
}
