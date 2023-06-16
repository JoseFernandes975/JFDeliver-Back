package com.josefernandes.jfdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josefernandes.jfdeliver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findAllByOrderByName();

}
