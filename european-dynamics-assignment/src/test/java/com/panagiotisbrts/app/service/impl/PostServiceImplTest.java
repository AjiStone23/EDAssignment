package com.panagiotisbrts.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.panagiotisbrts.app.exception.PostServiceException;
import com.panagiotisbrts.app.exception.UserServiceException;
import com.panagiotisbrts.app.io.entity.PostEntity;
import com.panagiotisbrts.app.io.entity.UserEntity;
import com.panagiotisbrts.app.io.repository.PostRepository;
import com.panagiotisbrts.app.io.repository.UserRepository;
import com.panagiotisbrts.app.shared.dto.PostDto;

class PostServiceImplTest {

	@InjectMocks
	PostServiceImpl postService;

	@Mock
	UserRepository userRepository;

	@Mock
	PostRepository postRepository;

	UserEntity userEntity;

	PostEntity postEntity;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		userEntity = new UserEntity();
		userEntity.setUserName("Mock userName");
		userEntity.setName("Mock Name");
		userEntity.setId(1L);
		userEntity.setEmail("Mock email");
		userEntity.setAvatar("Mock avatar");

		postEntity = new PostEntity();
		postEntity.setBody("Mock body");
		postEntity.setCreatedAt(Date.valueOf("2020-02-02"));
		postEntity.setId(1L);
		postEntity.setTitle("Mock title");

	}

	@Test
	void testGetUserPosts() {

		Optional<UserEntity> userEntityOptional = Optional.of(userEntity);

		List<PostEntity> postList = new ArrayList<>();

		postList.add(postEntity);

		when(userRepository.findById(anyLong())).thenReturn(userEntityOptional);

		when(postRepository.findAllByPostWriter(userEntity)).thenReturn(postList);

		List<PostDto> postDtoList = postService.getUserPosts(1L);

		for (PostDto postDto : postDtoList) {

			assertNotNull(postDto);
			assertEquals(Date.valueOf("2020-02-02"), postDto.getCreatedAt());
			assertEquals("Mock body", postDto.getBody());
			assertEquals(1L, postDto.getId());
			assertEquals("Mock title", postDto.getTitle());

		}

	}

	@Test
	final void testGetUserPosts_UserServiceException() {

		Optional<UserEntity> userEntityOptional = Optional.ofNullable(null);
		when(userRepository.findById(anyLong())).thenReturn(userEntityOptional);

		Exception exception = assertThrows(UserServiceException.class, () -> {
			postService.getUserPosts(1L);
		});

		String expectedMessage = "no user with id : " + 1;
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	void testGetPost() {

		Optional<PostEntity> postEntityOptional = Optional.of(postEntity);

		when(postRepository.findById(anyLong())).thenReturn(postEntityOptional);

		PostDto postDto = postService.getPost(1L);
		assertNotNull(postDto);

		assertNotNull(postDto);
		assertEquals(Date.valueOf("2020-02-02"), postDto.getCreatedAt());
		assertEquals("Mock body", postDto.getBody());
		assertEquals(1L, postDto.getId());
		assertEquals("Mock title", postDto.getTitle());

	}

	@Test
	final void testGetPost_PostServiceException() {

		Optional<PostEntity> postEntityOptional = Optional.ofNullable(null);
		when(postRepository.findById(anyLong())).thenReturn(postEntityOptional);

		Exception exception = assertThrows(PostServiceException.class, () -> {
			postService.getPost(1L);
		});

		String expectedMessage = "no post with id : " + 1;
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}
}
