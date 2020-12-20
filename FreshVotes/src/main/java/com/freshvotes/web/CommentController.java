package com.freshvotes.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.User;
import com.freshvotes.service.CommentService;
import com.freshvotes.service.UserService;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {

	@Autowired
	public CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseBody
	public List<Comment> getComments(@PathVariable Long featureId) {
		return commentService.findByFeatureId(featureId);
	}
	
	@PostMapping
	public String postComment(@PathVariable Long featureId, @PathVariable Long productId,
			Authentication authentication, Comment comment) {
		User user = userService.findByUsername(authentication.getName());
		Comment newComment = commentService.setCommentProperties(comment, featureId, user);
		
		commentService.save(newComment);
		
		return "redirect:/products/" + productId + "/features/" + featureId;
	}
}
