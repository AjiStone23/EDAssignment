package com.panagiotisbrts.app.service;

import java.util.List;

import com.panagiotisbrts.app.shared.dto.CommentDto;

/**
 * Interface of the service layer for the Comment Model
 * 
 * 
 */

public interface CommentService {

	/**
	 * Returns all {@link CommentDto} that comments the Post with the given id
	 * 
	 * @param postId the given Id
	 * 
	 * @return a {@link List} of {@link CommentDto} that comments the Post with the
	 *         given id
	 * 
	 * 
	 */
	List<CommentDto> getPostComments(long postId);

}
