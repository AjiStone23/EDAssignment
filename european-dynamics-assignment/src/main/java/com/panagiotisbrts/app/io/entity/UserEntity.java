package com.panagiotisbrts.app.io.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity class for Users that will be persisted in a SQL database
 * 
 */

@Entity(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	private String avatar;

	@Column(nullable = false, length = 50, unique = true)
	private String userName;

	@OneToMany(mappedBy = "postWriter", cascade = CascadeType.ALL)
	private List<PostEntity> posts;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
