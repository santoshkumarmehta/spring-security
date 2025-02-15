package in.kapurit.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.kapurit.entity.CustomerEntity;
import in.kapurit.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository crepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CustomerEntity c = crepo.findByName(username);

		return new User(c.getName(), c.getPwd(), Collections.emptyList());

	}



}
