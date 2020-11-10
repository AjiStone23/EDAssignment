package com.panagiotisbrts.app.shared.dto;

import java.util.List;

/**
 * A User POJO that is shared among different layers
 */

public class UserDto {

	private long id;
	private String name;
	private String email;
	private String avatar;
	private String username;
	private List<PostDto> posts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PostDto> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}

}
