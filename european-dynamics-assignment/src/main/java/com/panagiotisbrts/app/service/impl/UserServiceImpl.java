package com.panagiotisbrts.app.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.panagiotisbrts.app.exception.UserServiceException;
import com.panagiotisbrts.app.io.entity.UserEntity;
import com.panagiotisbrts.app.io.repository.UserRepository;
import com.panagiotisbrts.app.service.UserService;
import com.panagiotisbrts.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto getUserById(long id) {

		// a Library that helps us map the UserEntity to UserDto
		ModelMapper modelMapper = new ModelMapper();

		UserDto returnValue = new UserDto();

		Optional<UserEntity> userEntityOptional = userRepository.findById(id);

		// We throw a custom Exception if there is no such user, that we handle it with
		// ExceptionsHandler class
		if (!userEntityOptional.isPresent())
			throw new UserServiceException("no user with id : " + id);

		returnValue = modelMapper.map(userEntityOptional.get(), UserDto.class);

		return returnValue;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		// a Library that helps us map the UserEntity to UserDto
		ModelMapper modelMapper = new ModelMapper();

		List<UserDto> returnValue = new ArrayList<>();

		// Creating a PageRequest so we can use the Paging feature of
		// PagingAndSortingRepository interface
		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();

		// creating a TypeToken so we can copy the users List to the returnValue
		// List
		Type listType = new TypeToken<List<UserDto>>() {
		}.getType();

		returnValue = modelMapper.map(users, listType);

		return returnValue;
	}

	@Override
	public long getNumberOfUsers() {
		long returnValue = userRepository.count();
		return returnValue;
	}

}
