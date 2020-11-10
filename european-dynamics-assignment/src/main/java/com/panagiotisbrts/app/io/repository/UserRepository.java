package com.panagiotisbrts.app.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.panagiotisbrts.app.io.entity.UserEntity;

/**
 * Interface for {@link UserEntity} CRUD operations with additional paging and
 * sorting functionality , extends {@link PagingAndSortingRepository}
 * 
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
