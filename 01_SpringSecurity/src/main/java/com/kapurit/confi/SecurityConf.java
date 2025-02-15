package com.kapurit.confi;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConf {
	
	@Bean
	SecurityFilterChain securityFiletr( HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests(req->
			req.requestMatchers("/contact","/home").permitAll()
			.anyRequest()
			.authenticated())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
		
	}

	
	@Bean
	InMemoryUserDetailsManager inMemoryUsers() {
		
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("Santosh").password("password").build();
		
		UserDetails userDetails1 = User.withDefaultPasswordEncoder()
				.username("poonam").password("password").build();
		
	return new InMemoryUserDetailsManager(userDetails, userDetails1);
	} 
	
}
