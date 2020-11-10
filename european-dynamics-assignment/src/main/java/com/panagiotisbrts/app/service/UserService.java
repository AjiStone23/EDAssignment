package com.panagiotisbrts.app.service;

import java.util.List;

import com.panagiotisbrts.app.shared.dto.UserDto;

/**
 * Interface of the service layer for the User Model
 * 
 * 
 */
public interface UserService {
	/**
	 * Returns the {@link UserDto} with the given user_id
	 * 
	 * @param id the given user Id
	 * 
	 * @return the {@link UserDto} with the given user_id
	 * 
	 */
	UserDto getUserById(long id);

	/**
	 * Returns all the {@link UserDto} for the given page and limit
	 * 
	 * @param page  the given page starting from 0 for which we want the Users to be
	 *              returned
	 * 
	 * @param limit the max page capacity
	 * 
	 * @return all the {@link UserDto} for the given page and limit
	 * 
	 */

	List<UserDto> getUsers(int page, int limit);

	/**
	 * Returns the total number of Users
	 * 
	 */
	long getNumberOfUsers();
}
