package com.panagiotisbrts.app.shared.dto;

import java.util.Date;
import java.util.List;

/**
 * A Post POJO that is shared among different layers
 */

public class PostDto {

	private long id;
	private UserDto postWriter;
	private Date createdAt;
	private String title;
	private String body;
	private List<CommentDto> comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDto getPostWriter() {
		return postWriter;
	}

	public void setPostWriter(UserDto postWriter) {
		this.postWriter = postWriter;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

}
