package in.kapurit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kapurit.entity.CustomerEntity;
import in.kapurit.repository.CustomerRepository;
import in.kapurit.service.JwtService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	
	//Un-secured Access
	@GetMapping("/greet")
	public String greet() {
		return "welcome to all, GM";
	}

	//Secured Access
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to Kapur It";
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody CustomerEntity c) {
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(c.getName(), c.getPwd());

		try {
			Authentication authenticate = authenticationManager.authenticate(token);

			if (authenticate.isAuthenticated()) {
				String jwtToken = jwtService.generateToken(c.getName());
				return new ResponseEntity<>(jwtToken, HttpStatus.OK);
			}

		} catch (Exception e) {
			//logger
		}

		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/register")
	public String registerCustomer(@RequestBody CustomerEntity customer) {
		
		// duplicate check

		String encodedPwd = passwordEncoder.encode(customer.getPwd());
		customer.setPwd(encodedPwd);

		customerRepository.save(customer);

		return "User registered";
	}

}
