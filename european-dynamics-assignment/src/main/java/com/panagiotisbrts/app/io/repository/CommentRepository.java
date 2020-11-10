package com.panagiotisbrts.app.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panagiotisbrts.app.io.entity.CommentEntity;
import com.panagiotisbrts.app.io.entity.PostEntity;

/**
 * Interface for {@link CommentEntity} CRUD operations, extends
 * {@link CrudRepository}
 * 
 * 
 */

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

	/**
	 * Returns all {@link CommentEntity} that are commenting the given
	 * {@link PostEntity}
	 * 
	 * @param commentedPost {@link PostEntity}
	 * 
	 * @return a {@link List} of the {@link CommentEntity } of the given
	 *         {@link PostEntity}
	 */
	List<CommentEntity> findAllByCommentedPost(PostEntity commentedPost);

}
