package com.panagiotisbrts.app.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.panagiotisbrts.app.io.entity.PostEntity;
import com.panagiotisbrts.app.io.entity.UserEntity;

/**
 * Interface for {@link PostEntity} CRUD operations, extends
 * {@link CrudRepository}.
 * 
 */

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

	/**
	 * Returns all {@link PostEntity} that are written by the given
	 * {@link UserEntity}
	 * 
	 * @param postWriter the given {@link PostEntity}
	 * 
	 * @return a {@link List} of the {@link PostEntity } written by the given
	 *         {@link UserEntity}
	 * 
	 */

	List<PostEntity> findAllByPostWriter(UserEntity postWriter);

}
