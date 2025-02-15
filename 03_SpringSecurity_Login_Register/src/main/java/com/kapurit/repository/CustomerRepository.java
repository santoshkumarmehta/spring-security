package com.kapurit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kapurit.entity.CustomerEntity;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

	CustomerEntity findByEmail(String email);

}
