package com.panagiotisbrts.app.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.panagiotisbrts.app.ui.model.response.ErrorMessage;

/**
 * A class that handles the custom Exceptions
 * 
 */

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {

		ErrorMessage errorMesage = new ErrorMessage(new Date(), ex.getMessage());

		return new ResponseEntity<>(errorMesage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { PostServiceException.class })
	public ResponseEntity<Object> handlePostServiceException(PostServiceException ex, WebRequest request) {

		ErrorMessage errorMesage = new ErrorMessage(new Date(), ex.getMessage());

		return new ResponseEntity<>(errorMesage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
