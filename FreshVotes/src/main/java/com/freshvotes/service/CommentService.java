package com.freshvotes.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	public CommentRepository commentRepo;
	
	@Autowired
	public FeatureService featureService;
	
	public List<Comment> findByFeatureId(Long featureId) {
		return commentRepo.findByFeatureId(featureId);
	}
	
	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public Comment setCommentProperties(Comment comment, Long featureId, User user) {
		
		Optional<Feature> featureOpt = featureService.findById(featureId);
		
		if(featureOpt.isPresent()) {
			Feature feature = featureOpt.get();
			comment.setFeature(feature);
		}
		
		comment.setUser(user);
		comment.setCreatedDate(new Date());

		return comment;
	}
}
