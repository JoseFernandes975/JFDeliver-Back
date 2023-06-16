package com.josefernandes.jfdeliver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josefernandes.jfdeliver.dtos.ProductDTO;
import com.josefernandes.jfdeliver.entities.Product;
import com.josefernandes.jfdeliver.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<ProductDTO> findAll(){
	 List<Product> list = repository.findAllByOrderByName();
	 return list.stream().map(x -> new ProductDTO(x)).toList();
	}

}
