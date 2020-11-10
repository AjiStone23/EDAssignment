package com.panagiotisbrts.app.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * The Controller class of the app
 */

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@Autowired
	CommentService commentService;

	/**
	 * Client requests for a {@link SingleUserRest} given an id
	 * 
	 * @param id the given id
	 * 
	 * @return a {@link SingleUserRest}
	 * 
	 * 
	 */
	@GetMapping(path = "/{id}")
	public SingleUserRest getUser(@PathVariable long id) {

		// a Library that helps us map the UserDto to SingleUserRest
		ModelMapper modelMapper = new ModelMapper();

		SingleUserRest returnValue = new SingleUserRest();

		UserDto userDto = userService.getUserById(id);

		returnValue = modelMapper.map(userDto, SingleUserRest.class);

		return returnValue;
	}

	/**
	 * Client requests for all Users in a single Page, given page and limit.
	 * 
	 * 
	 * @param page  the requested page starting from 0. When there is no page
	 *              parameter on the request , the default value is 0.
	 * @param limit the max capacity of every page. When there is no limit parameter
	 *              on the request , the default value is 25.
	 * 
	 * @return a {@link UsersRest}
	 * 
	 */

	@GetMapping
	public UsersRest getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		// a Library that helps us map the UserDto to SingleUserRest
		ModelMapper modelMapper = new ModelMapper();

		UsersRest returnValue = new UsersRest();
		List<SingleUserRest> userList = new ArrayList<>();

		List<UserDto> usersDto = userService.getUsers(page, limit);

		// creating a TypeToken so we can copy the usersDto List to the returnValue List
		java.lang.reflect.Type listType = new TypeToken<List<SingleUserRest>>() {
		}.getType();

		userList = modelMapper.map(usersDto, listType);

		long total = userService.getNumberOfUsers();

		returnValue.setItems(userList);
		returnValue.setTotal(total);

		return returnValue;
	}

	/**
	 * Client requests for all {@link PostRest} of a given id of a User.
	 * 
	 * @param id the given id of the User
	 * 
	 * @return a {@link list} of {@link PostRest}
	 * 
	 */

	@GetMapping(path = "/{id}/posts")
	public List<PostRest> getUserPosts(@PathVariable Long id) {

		// a Library that helps us map the PostDto to PostRest
		ModelMapper modelMapper = new ModelMapper();

		// explicitly define mappings between properties with the use of lamda
		// expressions
		modelMapper.typeMap(PostDto.class, PostRest.class).addMappings(mapper -> {
			mapper.map(src -> src.getPostWriter().getId(), PostRest::setUserId);

		});

		List<PostRest> returnValue = new ArrayList<>();

		List<PostDto> postsDto = postService.getUserPosts(id);

		Type listType = new TypeToken<List<PostRest>>() {
		}.getType();

		returnValue = modelMapper.map(postsDto, listType);

		return returnValue;
	}

	/**
	 * Client requests for a {@link PostRest} with a given post id .
	 * 
	 * @param postId the given id of the post
	 * 
	 * @return a {@link PostRest}
	 * 
	 */
	@GetMapping(path = "/{userId}/posts/{postId}")
	public PostRest getUserPost(@PathVariable Long postId) {

		// a Library that helps us map the PostDto to PostRest
		ModelMapper modelMapper = new ModelMapper();

		// explicitly define mappings between properties with the use of lamda
		// expressions
		modelMapper.typeMap(PostDto.class, PostRest.class).addMappings(mapper -> {
			mapper.map(src -> src.getPostWriter().getId(), PostRest::setUserId);

		});

		PostRest returnValue = new PostRest();

		PostDto postDto = postService.getPost(postId);

		returnValue = modelMapper.map(postDto, PostRest.class);

		return returnValue;
	}

	/**
	 * Client requests for all {@link CommentRest} of a Post with a given post id .
	 * 
	 * @param postId the given id of the post
	 * 
	 * @return a {@link list} of {@link CommentRest}
	 * 
	 */
	@GetMapping(path = "/{userId}/posts/{postId}/comments")
	public List<CommentRest> getPostComments(@PathVariable Long postId) {

		ModelMapper modelMapper = new ModelMapper();

		// Configuring mapping to a matching strategy, which more loosely matches
		// property names
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		List<CommentRest> returnValue = new ArrayList<>();

		List<CommentDto> commentsDto = commentService.getPostComments(postId);

		Type listType = new TypeToken<List<CommentRest>>() {
		}.getType();

		returnValue = modelMapper.map(commentsDto, listType);

		return returnValue;
	}

}
