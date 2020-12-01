package com.freshvotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;

public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findByUser(User user) {
		return productRepo.findByUser(user);
	}
}
