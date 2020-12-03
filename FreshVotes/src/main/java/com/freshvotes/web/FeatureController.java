package com.freshvotes.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.service.FeatureService;
import com.freshvotes.service.ProductService;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

	@Autowired
	FeatureService featureService;
	
	@PostMapping("")
	public String createFeature(@PathVariable Long productId) {
		featureService.createFeature(productId);
		
		return "feautre";
	}
}
