package com.panagiotisbrts.app.ui.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.panagiotisbrts.app.service.CommentService;
import com.panagiotisbrts.app.service.PostService;
import com.panagiotisbrts.app.service.UserService;
import com.panagiotisbrts.app.shared.dto.CommentDto;
import com.panagiotisbrts.app.shared.dto.PostDto;
import com.panagiotisbrts.app.shared.dto.UserDto;
import com.panagiotisbrts.app.ui.model.response.CommentRest;
import com.panagiotisbrts.app.ui.model.response.PostRest;
import com.panagiotisbrts.app.ui.model.response.SingleUserRest;
import com.panagiotisbrts.app.ui.model.response.UsersRest;

class UserControllerTest {

	@InjectMocks
	UserController usercontroller;

	@Mock
	UserService userService;

	@Mock
	PostService postService;

	@Mock
	CommentService commentService;

	UserDto userDto;
	PostDto postDto;
	CommentDto commentDto;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userDto = new UserDto();
		userDto.setUsername("Mock userName");
		userDto.setName("Mock Name");
		userDto.setId(1L);
		userDto.setEmail("Mock email");
		userDto.setAvatar("Mock avatar");

		postDto = new PostDto();
		postDto.setBody("Mock post body");
		postDto.setCreatedAt(Date.valueOf("2020-03-02"));
		postDto.setId(2L);
		postDto.setTitle("Mock post title");
		postDto.setPostWriter(userDto);

		commentDto = new CommentDto();
		commentDto.setBody("Mock comment body");
		commentDto.setCreatedAt(Date.valueOf("2020-02-02"));
		commentDto.setId(3L);
		commentDto.setCommentWriter(userDto);
		commentDto.setCommentedPost(postDto);

	}

	@Test
	void testGetUser() {

		when(userService.getUserById(anyLong())).thenReturn(userDto);

		SingleUserRest singleUserRest = usercontroller.getUser(1L);
		assertNotNull(singleUserRest);
		assertEquals("Mock userName", singleUserRest.getUsername());
		assertEquals("Mock Name", singleUserRest.getName());
		assertEquals("Mock email", singleUserRest.getEmail());
		assertEquals("Mock avatar", singleUserRest.getAvatar());
		assertEquals(1L, singleUserRest.getId());
	}

	@Test
	void testGetUsers() {
		List<UserDto> usersDto = new ArrayList<>();
		usersDto.add(userDto);
		when(userService.getUsers(anyInt(), anyInt())).thenReturn(usersDto);
		when(userService.getNumberOfUsers()).thenReturn(1L);

		UsersRest usersRest = usercontroller.getUsers(1, 1);

		assertEquals(1, usersRest.getTotal());

		List<SingleUserRest> items = usersRest.getItems();

		assertNotNull(items);
		assertFalse(items.isEmpty());
		for (SingleUserRest singleUserRest : items) {
			assertEquals("Mock userName", singleUserRest.getUsername());
			assertEquals("Mock Name", singleUserRest.getName());
			assertEquals("Mock email", singleUserRest.getEmail());
			assertEquals("Mock avatar", singleUserRest.getAvatar());
			assertEquals(1L, singleUserRest.getId());

		}

	}

	@Test
	void testGetUserPosts() {

		List<PostDto> postsList = new ArrayList<>();
		postsList.add(postDto);

		when(postService.getUserPosts(anyLong())).thenReturn(postsList);

		List<PostRest> postsRestList = usercontroller.getUserPosts(1L);
		assertNotNull(postsRestList);
		assertFalse(postsRestList.isEmpty());

		for (PostRest postRest : postsRestList) {
			assertEquals(Date.valueOf("2020-03-02"), postRest.getCreatedAt());
			assertEquals("Mock post body", postRest.getBody());
			assertEquals(1L, postRest.getUserId());
			assertEquals(2L, postRest.getId());
			assertEquals("Mock post title", postRest.getTitle());
		}
	}

	@Test
	void testGetUserPost() {

		when(postService.getPost(anyLong())).thenReturn(postDto);

		PostRest postRest = usercontroller.getUserPost(1L);

		assertEquals(Date.valueOf("2020-03-02"), postRest.getCreatedAt());
		assertEquals("Mock post body", postRest.getBody());
		assertEquals(1L, postRest.getUserId());
		assertEquals(2L, postRest.getId());
		assertEquals("Mock post title", postRest.getTitle());

	}

	@Test
	void testGetPostComments() {

		List<CommentDto> commentDtoList = new ArrayList<>();

		commentDtoList.add(commentDto);

		when(commentService.getPostComments(anyLong())).thenReturn(commentDtoList);

		List<CommentRest> commentsRestList = usercontroller.getPostComments(1L);

		assertNotNull(commentsRestList);
		assertFalse(commentsRestList.isEmpty());

		for (CommentRest commentRest : commentsRestList) {
			assertEquals(Date.valueOf("2020-02-02"), commentRest.getCreatedAt());
			assertEquals("Mock comment body", commentRest.getBody());
			assertEquals("Mock avatar", commentRest.getAvatar());
			assertEquals(3L, commentRest.getId());
			assertEquals("Mock email", commentRest.getEmail());
			assertEquals("Mock Name", commentRest.getName());
			assertEquals(2L, commentRest.getPostId());

		}

	}

}
