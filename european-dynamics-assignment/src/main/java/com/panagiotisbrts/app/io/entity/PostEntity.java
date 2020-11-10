package com.panagiotisbrts.app.io.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity class for Posts that will be persisted in a SQL database
 * 
 */

@Entity(name = "posts")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity postWriter;

	private Date createdAt;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 500)
	private String body;

	@OneToMany(mappedBy = "commentedPost", cascade = CascadeType.ALL)
	private List<CommentEntity> comments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getPostWriter() {
		return postWriter;
	}

	public void setPostWriter(UserEntity postWriter) {
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

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}
