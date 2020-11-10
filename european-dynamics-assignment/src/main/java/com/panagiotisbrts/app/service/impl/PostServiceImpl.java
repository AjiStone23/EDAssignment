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
import com.panagiotisbrts.app.exception.UserServiceException;
import com.panagiotisbrts.app.io.entity.PostEntity;
import com.panagiotisbrts.app.io.entity.UserEntity;
import com.panagiotisbrts.app.io.repository.PostRepository;
import com.panagiotisbrts.app.io.repository.UserRepository;
import com.panagiotisbrts.app.service.PostService;
import com.panagiotisbrts.app.shared.dto.PostDto;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Override
	public List<PostDto> getUserPosts(long user_id) {

		List<PostDto> returnValue = new ArrayList<>();

		// a Library that helps us map the PostEntity to PostDto
		ModelMapper modelMapper = new ModelMapper();

		Optional<UserEntity> userEntity = userRepository.findById(user_id);

		// We throw a custom Exception if there is no such user, that we handle it with
		// ExceptionsHandler class
		if (!userEntity.isPresent())
			throw new UserServiceException("no user with id : " + user_id);

		List<PostEntity> userPosts = postRepository.findAllByPostWriter(userEntity.get());

		// creating a TypeToken so we can copy the userPosts List to the returnValue
		// List
		Type listType = new TypeToken<List<PostDto>>() {
		}.getType();

		returnValue = modelMapper.map(userPosts, listType);

		return returnValue;

	}

	@Override
	public PostDto getPost(long postId) {

		PostDto returnValue = new PostDto();

		// a Library that helps us map the PostEntity to PostDto
		ModelMapper modelMapper = new ModelMapper();

		Optional<PostEntity> postEntity = postRepository.findById(postId);

		// We throw a custom Exception if there is no such post, that we handle it with
		// ExceptionsHandler class
		if (!postEntity.isPresent())
			throw new PostServiceException("no post with id : " + postId);

		returnValue = modelMapper.map(postEntity.get(), PostDto.class);

		return returnValue;

	}

}
