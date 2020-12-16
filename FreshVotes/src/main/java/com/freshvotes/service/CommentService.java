package com.freshvotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freshvotes.domain.Comment;
import com.freshvotes.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	public CommentRepository commentRepo;
	
	public List<Comment> findByFeatureId(Long featureId) {
		return commentRepo.findByFeatureId(featureId);
	}
}
