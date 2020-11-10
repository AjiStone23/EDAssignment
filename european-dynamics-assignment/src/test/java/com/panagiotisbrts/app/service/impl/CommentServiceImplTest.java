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
import com.panagiotisbrts.app.io.entity.CommentEntity;
import com.panagiotisbrts.app.io.entity.PostEntity;
import com.panagiotisbrts.app.io.repository.CommentRepository;
import com.panagiotisbrts.app.io.repository.PostRepository;
import com.panagiotisbrts.app.shared.dto.CommentDto;

class CommentServiceImplTest {

	@InjectMocks
	CommentServiceImpl commentService;

	@Mock
	PostRepository postRepository;

	@Mock
	CommentRepository commentRepository;

	PostEntity postEntity;

	CommentEntity commentEntity;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		postEntity = new PostEntity();
		postEntity.setBody("Mock body");
		postEntity.setCreatedAt(Date.valueOf("2020-02-02"));
		postEntity.setId(1L);
		postEntity.setTitle("Mock title");

		commentEntity = new CommentEntity();

		commentEntity.setBody("Mock body");
		commentEntity.setCreatedAt(Date.valueOf("2020-02-02"));
		commentEntity.setId(1L);
	}

	@Test
	void testGetPostComments() {

		Optional<PostEntity> postEntityOptional = Optional.of(postEntity);

		List<CommentEntity> commentList = new ArrayList<>();

		commentList.add(commentEntity);

		when(postRepository.findById(anyLong())).thenReturn(postEntityOptional);

		when(commentRepository.findAllByCommentedPost(postEntity)).thenReturn(commentList);

		List<CommentDto> commentDtoList = commentService.getPostComments(1L);

		for (CommentDto commentDto : commentDtoList) {

			assertNotNull(commentDto);
			assertEquals(Date.valueOf("2020-02-02"), commentDto.getCreatedAt());
			assertEquals("Mock body", commentDto.getBody());
			assertEquals(1L, commentDto.getId());

		}

	}

	@Test
	final void testGetPostComments_PostServiceException() {

		Optional<PostEntity> postEntityOptional = Optional.ofNullable(null);
		when(postRepository.findById(anyLong())).thenReturn(postEntityOptional);

		Exception exception = assertThrows(PostServiceException.class, () -> {
			commentService.getPostComments(1L);
		});

		String expectedMessage = "no post with id : " + 1;
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

}
