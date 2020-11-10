package com.panagiotisbrts.app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.panagiotisbrts.app.exception.UserServiceException;
import com.panagiotisbrts.app.io.entity.UserEntity;
import com.panagiotisbrts.app.io.repository.UserRepository;
import com.panagiotisbrts.app.shared.dto.UserDto;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userEntity = new UserEntity();
		userEntity.setUserName("Mock userName");
		userEntity.setName("Mock Name");
		userEntity.setId(1L);
		userEntity.setEmail("Mock email");
		userEntity.setAvatar("Mock avatar");

	}

	@Test
	final void testGetUserById() {

		Optional<UserEntity> userEntityOptional = Optional.of(userEntity);

		when(userRepository.findById(anyLong())).thenReturn(userEntityOptional);

		UserDto userDto = userService.getUserById(1L);
		assertNotNull(userDto);
		assertEquals("Mock userName", userDto.getUsername());
		assertEquals("Mock Name", userDto.getName());
		assertEquals("Mock email", userDto.getEmail());
		assertEquals("Mock avatar", userDto.getAvatar());
		assertEquals(1L, userDto.getId());

	}

	@Test
	final void testGetUserById_UserServiceException() {

		Optional<UserEntity> userEntityOptional = Optional.ofNullable(null);
		when(userRepository.findById(anyLong())).thenReturn(userEntityOptional);

		Exception exception = assertThrows(UserServiceException.class, () -> {
			userService.getUserById(1L);
		});

		String expectedMessage = "no user with id : " + 1;
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	final void testGetUsers() {

		List<UserEntity> userList = new ArrayList<>();

		userList.add(userEntity);
		Page<UserEntity> usersPage = new PageImpl<UserEntity>(userList);

		when(userRepository.findAll((Pageable) any())).thenReturn(usersPage);

		List<UserDto> userDtoList = userService.getUsers(1, 1);

		for (UserDto userDto : userDtoList) {

			assertNotNull(userDto);
			assertEquals("Mock userName", userDto.getUsername());
			assertEquals("Mock Name", userDto.getName());
			assertEquals("Mock email", userDto.getEmail());
			assertEquals("Mock avatar", userDto.getAvatar());
			assertEquals(1L, userDto.getId());

		}

	}

}
