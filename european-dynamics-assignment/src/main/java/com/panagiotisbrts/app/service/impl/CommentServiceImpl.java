package com.panagiotisbrts.app.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panagiotisbrts.app.exception.PostServiceException;
import com.panagiotisbrts.app.io.entity.CommentEntity;
import com.panagiotisbrts.app.io.entity.PostEntity;
import com.panagiotisbrts.app.io.repository.CommentRepository;
import com.panagiotisbrts.app.io.repository.PostRepository;
import com.panagiotisbrts.app.service.CommentService;
import com.panagiotisbrts.app.shared.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	@Override
	public List<CommentDto> getPostComments(long postId) {

		List<CommentDto> returnValue = new ArrayList<>();

		// a Library that helps us map the CommentEntity to CommentDto
		ModelMapper modelMapper = new ModelMapper();

		Optional<PostEntity> postEntity = postRepository.findById(postId);

		// We throw a custom Exception if there is no such post, that we handle it with
		// ExceptionsHandler class
		if (!postEntity.isPresent())
			throw new PostServiceException("no post with id : " + postId);

		List<CommentEntity> postComments = commentRepository.findAllByCommentedPost(postEntity.get());

		// creating a TypeToken so we can copy the postComments List to the returnValue
		// List
		Type listType = new TypeToken<List<CommentDto>>() {

		}.getType();

		returnValue = modelMapper.map(postComments, listType);

		return returnValue;

	}

}
