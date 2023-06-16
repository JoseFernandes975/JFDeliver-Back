package com.josefernandes.jfdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.josefernandes.jfdeliver.dtos.OrderWithProductsDTO;
import com.josefernandes.jfdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService services;
	
	@GetMapping
	public ResponseEntity<List<OrderWithProductsDTO>> findAll(){
		List<OrderWithProductsDTO> dtos = services.findAll();
		return ResponseEntity.ok().body(dtos);
	}
	
	@PostMapping
	public ResponseEntity<OrderWithProductsDTO> insert(@RequestBody OrderWithProductsDTO dto){
		dto = services.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderWithProductsDTO> updateDelivered(@PathVariable Long id){
		OrderWithProductsDTO dto = services.updateDelivered(id);
		return ResponseEntity.ok().body(dto);
		
	}
	
	
}
