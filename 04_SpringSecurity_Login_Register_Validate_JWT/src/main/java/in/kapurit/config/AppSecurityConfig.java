package in.kapurit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.kapurit.filter.AppFilter;
import in.kapurit.service.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	@Autowired
	private AppFilter appFilter;
	
	@Autowired
	private CustomerService customerService;
	
	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=
        		new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerService);
        authenticationProvider.setPasswordEncoder(pwdEncoder());
        return authenticationProvider;
    }

	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
		
	 
		/*
		 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
		 * throws Exception { return http.disable() .authorizeHttpRequests()
		 * .requestMatchers("/api/login","/api/register").permitAll() .and()
		 * .authorizeHttpRequests().requestMatchers("/api/**") .authenticated() .and()
		 * .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 * .and() .authenticationProvider(authenticationProvider())
		 * .addFilterBefore(appFilter,
		 * UsernamePasswordAuthenticationFilter.class).build();
		 * 
		 * }
		 */
	 
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .csrf(csrf -> csrf.disable()) // Disable CSRF
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/api/login", "/api/register", "/api/greet").permitAll() // Public endpoints
	                        .requestMatchers("/api/**").authenticated() // Secured endpoints
	                )
	                .sessionManagement(session -> session
	                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
	                )
	                .authenticationProvider(authenticationProvider()) // Custom authentication provider
	                .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class) // JWT Filter
	                .build();
	    }
}
