package com.josefernandes.jfdeliver.services;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josefernandes.jfdeliver.dtos.OrderWithProductsDTO;
import com.josefernandes.jfdeliver.dtos.ProductDTO;
import com.josefernandes.jfdeliver.entities.Order;
import com.josefernandes.jfdeliver.entities.Product;
import com.josefernandes.jfdeliver.enums.OrderStatus;
import com.josefernandes.jfdeliver.repositories.OrderRepository;
import com.josefernandes.jfdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<OrderWithProductsDTO> findAll(){
		List<Order> result = repository.findOrderWithProducts();
		return result.stream().map(x -> new OrderWithProductsDTO(x)).toList();
	}

	@Transactional
	public OrderWithProductsDTO insert(OrderWithProductsDTO dto) {
		Order order = new Order();
	    order.setAddress(dto.getAddress());
	    order.setLatitude(dto.getLatitude());
	    order.setLongitude(dto.getLongitude());
	    order.setMoment(Instant.now());
	    order.setStatus(OrderStatus.PENDING);
	    for(ProductDTO x: dto.getProducts()) {
	    	Product product = productRepository.getOne(x.getId());
	    	order.getProducts().add(product);
	    }
	    
	    order = repository.save(order);
	    
	    return new OrderWithProductsDTO(order);
	}
	
	@Transactional
	public OrderWithProductsDTO updateDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderWithProductsDTO(order);
	}
	
}
