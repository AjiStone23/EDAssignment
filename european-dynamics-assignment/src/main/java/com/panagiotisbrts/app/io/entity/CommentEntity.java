package com.panagiotisbrts.app.io.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity class for Comments that will be persisted in a SQL database
 * 
 */

@Entity(name = "comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity commentWriter;

	@ManyToOne
	@JoinColumn(name = "posts_id")
	private PostEntity commentedPost;

	@Column(nullable = false)
	private Date createdAt;

	@Column(nullable = false, length = 500)
	private String Body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(UserEntity commentWriter) {
		this.commentWriter = commentWriter;
	}

	public PostEntity getCommentedPost() {
		return commentedPost;
	}

	public void setCommentedPost(PostEntity commentedPost) {
		this.commentedPost = commentedPost;
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
