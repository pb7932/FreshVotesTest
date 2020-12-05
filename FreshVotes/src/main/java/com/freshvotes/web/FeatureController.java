package com.freshvotes.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("{featureId}") 
	public String feature(@PathVariable Long featureId, @PathVariable Long productId, ModelMap model) {
		Optional<Feature> featureOpt = featureService.findById(featureId);
		
		if(featureOpt.isPresent()) {
			Feature feature = featureOpt.get();
			model.put("feature", feature);
		}
		
		//TODO handle a situation where feature is not found
		return "feature";
	}
		
	@PostMapping("{featureId}") 
	public String updateFeature(Feature feature, @PathVariable Long featureId, @PathVariable Long productId) {
		feature = featureService.save(feature);
		return "redirect:/p/"+ feature.getProduct().getName();
	}
	
	
	@PostMapping("")
	public String createFeature(@PathVariable Long productId) {
		Feature feature = featureService.createFeature(productId);
		
		return "redirect:/products/" + productId + "/features/" + feature.getId();
	}
	
	
}
