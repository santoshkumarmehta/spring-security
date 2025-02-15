package com.kapurit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.kapurit.service.CustomerService;

@Configuration
@EnableWebSecurity
public class CustomerConfiguration {

	@Autowired
	private CustomerService customerService;

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(customerService);
		authProvider.setPasswordEncoder(pwdEncoder());

		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests( req -> {
			req.requestMatchers("/register", "/login")
			   .permitAll()
			   .anyRequest()
			   .authenticated();			
		});
		
		return http.csrf().disable().build();
	}
}
