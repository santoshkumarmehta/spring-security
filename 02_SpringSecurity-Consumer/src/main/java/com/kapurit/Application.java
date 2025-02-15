package com.kapurit;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		//Using RestTemplate
		
		/*
		 * HttpHeaders headers=new HttpHeaders(); headers.setBasicAuth("santosh",
		 * "password");
		 * 
		 * HttpEntity<String> entity=new HttpEntity<String>(headers);
		 * 
		 * String apiURL="http://localhost:8080/greet";
		 * 
		 * RestTemplate restTemplate=new RestTemplate();
		 * 
		 * ResponseEntity<String> responseEntity=restTemplate.exchange(apiURL,
		 * HttpMethod.GET, entity, String.class);
		 * System.out.println(responseEntity.getBody());
		 */
		
		// Using Web Client
		String apiURL="http://localhost:8080/greet";
		WebClient client = WebClient.create();
		String userName="santosh";
		String password="password";
		String auth = userName + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
		String response =  client.get( )
						   	     .uri(apiURL)
						   	     .header("Authorization", "Basic "+ encodedAuth)
						   	     .retrieve( )
						   	     .bodyToMono(String.class)
						   	     .block( );

		System.out.println(response);
	}

}
