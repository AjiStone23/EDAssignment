package com.panagiotisbrts.app.exception;

import com.panagiotisbrts.app.service.impl.UserServiceImpl;

/**
 * A custom exception class for the {@link UserServiceImpl} class, extends
 * {@link RuntimeException}
 * 
 */

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -8827000760450323051L;

	public UserServiceException(String message) {
		super(message);

	}

}
