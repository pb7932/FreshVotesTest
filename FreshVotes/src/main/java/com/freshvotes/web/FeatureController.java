package com.freshvotes.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
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
		
		Optional<Feature> featureOpt = featureService.findById(featureId);
		
		if(featureOpt.isPresent()) {
			Feature feature = featureOpt.get();
			model.put("feature", feature);
			model.put("comments", getCommentsWithoutDuplicates(feature));
		}
		model.put("user", user);
		
		//TODO handle a situation where feature is not found
		return "feature";
	}

	private Set<Comment> getCommentsWithoutDuplicates(Feature feature) {
		Set<Comment> comments = feature.getComments();
		return comments;
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
