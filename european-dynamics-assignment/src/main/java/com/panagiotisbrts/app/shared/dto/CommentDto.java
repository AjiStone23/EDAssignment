package com.panagiotisbrts.app.shared.dto;

import java.util.Date;

/**
 * A Comment POJO that is shared among different layers
 */

public class CommentDto {

	private long id;
	private UserDto commentWriter;
	private PostDto commentedPost;
	private Date createdAt;
	private String Body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PostDto getCommentedPost() {
		return commentedPost;
	}

	public void setCommentedPost(PostDto commentedPost) {
		this.commentedPost = commentedPost;
	}

	public UserDto getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(UserDto commentWriter) {
		this.commentWriter = commentWriter;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

}
