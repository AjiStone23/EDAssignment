package com.panagiotisbrts.app.service;

import java.util.List;

import com.panagiotisbrts.app.shared.dto.PostDto;

/**
 * Interface of the service layer for the Post Model
 * 
 * 
 */
public interface PostService {

	/**
	 * Returns all {@link PostDto} written by the User with the given user_id
	 * 
	 * @param user_id the given user Id
	 * 
	 * @return a {@link List} of {@link PostDto} written by the User with the given
	 *         user_id
	 * 
	 * 
	 * 
	 */
	List<PostDto> getUserPosts(long user_id);

	/**
	 * Returns the {@link PostDto} with the given postId
	 * 
	 * @param postId the given postId
	 * 
	 * @return a {@link PostDto} with the given postId
	 * 
	 * 
	 */

	PostDto getPost(long postId);

}
