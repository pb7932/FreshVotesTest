package com.freshvotes.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
import com.freshvotes.service.ProductService;
import com.freshvotes.service.UserService;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

	@Autowired
	FeatureService featureService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("{featureId}") 
	public String feature(Authentication authentication, @PathVariable Long featureId, @PathVariable Long productId, ModelMap model) {
		User user = userService.findByUsername(authentication.getName());
		model.put("user", user);
		Optional<Feature> featureOpt = featureService.findById(featureId);
		
		if(featureOpt.isPresent()) {
			Feature feature = featureOpt.get();
			model.put("feature", feature);
		}
		
		//TODO handle a situation where feature is not found
		return "feature";
	}
		
	@PostMapping("{featureId}") 
	public String updateFeature(Authentication authentication, Feature feature, @PathVariable Long featureId, @PathVariable Long productId) {
		User user = userService.findByUsername(authentication.getName());
		feature.setUser(user);
		feature = featureService.save(feature);
		return "redirect:/p/"+ feature.getProduct().getName();
	}
	
	
	@PostMapping("")
	public String createFeature(Authentication authentication, @PathVariable Long productId) {
		User user = userService.findByUsername(authentication.getName());
		Feature feature = featureService.createFeature(productId, user);
		
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}
	
	
}
