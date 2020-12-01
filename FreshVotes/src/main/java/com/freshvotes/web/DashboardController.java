package com.freshvotes.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;
import com.freshvotes.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String rootView() {
		return "index";
	}
	
	@GetMapping("/dashboard") 
	public String dashboardView(ModelMap model, Authentication authentication) {
		User user = userService.findByUsername(authentication.getName());
		List<Product> products = productRepo.findByUser(user);
		
		model.put("products", products);
		return "dashboard";
	}
}
