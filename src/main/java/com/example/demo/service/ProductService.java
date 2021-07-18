package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

}
