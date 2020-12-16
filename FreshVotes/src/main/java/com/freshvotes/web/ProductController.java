package com.freshvotes.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.service.ProductService;
import com.freshvotes.service.UserService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable Long productId, ModelMap model, HttpServletResponse response) throws IOException {
		Optional<Product> productOpt = productService.findById(productId);
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			model.put("product", product);
		}
		else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "product with id " + productId + " was not found");
			return "product";
		}
		
		return "product";
	}
	
	@GetMapping("/p/{productName}") 
		public String productUserView(@PathVariable String productName, ModelMap model) {
		if(productName != null) {
			Optional<Product> productOpt = productService.findByName(productName);
			
			if(productOpt.isPresent()) {
				model.put("product", productOpt.get());
			}
		}
		return "productUserView";
	}
	
	@PostMapping("/products") 
	public String createProduct(Authentication authentication) {
		Product product = new Product();
		
		User user = userService.findByUsername(authentication.getName());
		
		product.setPublished(false);
		product.setUser(user);
		
		product = productService.save(product);
		return "redirect:/products/"+product.getId();
	}
	
	@PostMapping("/products/{productId}") 
	public String saveProduct(@PathVariable Long productId, Product product) {
		product = productService.save(product);
		
		return "redirect:/products/"+product.getId();
	}
	
}
