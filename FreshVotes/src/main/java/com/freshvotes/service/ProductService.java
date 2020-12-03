package com.freshvotes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findByUser(User user) {
		return productRepo.findByUser(user);
	}
	
	public Optional<Product> findById(Long productId) {
		return productRepo.findById(productId);
	}
	
	public Product save(Product product) {
		return productRepo.save(product);
	}
}
