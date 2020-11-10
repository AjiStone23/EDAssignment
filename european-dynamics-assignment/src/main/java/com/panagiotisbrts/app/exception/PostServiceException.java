package com.panagiotisbrts.app.exception;

import com.panagiotisbrts.app.service.impl.PostServiceImpl;

/**
 * A custom exception class for the {@link PostServiceImpl} class, extends
 * {@link RuntimeException}
 * 
 */

public class PostServiceException extends RuntimeException {

	private static final long serialVersionUID = -8827000760450323051L;

	public PostServiceException(String message) {
		super(message);

	}

}
