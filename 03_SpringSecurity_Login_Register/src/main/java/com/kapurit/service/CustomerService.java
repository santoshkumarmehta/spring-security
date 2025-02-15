package com.kapurit.service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kapurit.entity.CustomerEntity;
import com.kapurit.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepo;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		CustomerEntity c = customerRepo.findByEmail(email);

		return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
	}
}
