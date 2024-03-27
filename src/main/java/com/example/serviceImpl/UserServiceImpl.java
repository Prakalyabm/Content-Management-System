package com.example.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.DTO.UserRequest;
import com.example.cms.DTO.UserResponse;
import com.example.cms.model.User;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponeStructure;
import com.example.exception.UserAlreadyExistByEmailException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository respository;
	private ResponeStructure<UserResponse> responeStructure;
	private PasswordEncoder passwordEncoder;
	

	@Override
	public ResponseEntity<ResponeStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(respository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException(null);
		
		User user =respository.save(mapToUserEntity(userRequest, new User()));
		
		return ResponseEntity.ok(responeStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("User Register Successfully")
				.setBody(mapToUserResponse(user)));
	}
	
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.build();
	}

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		return user;
	}


}
