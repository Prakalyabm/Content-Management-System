package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.DTO.UserRequest;
import com.example.cms.DTO.UserResponse;
import com.example.cms.model.User;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponeStructure;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
public class userController {
private UserService userService;


@PostMapping("/users/register")

public ResponseEntity<ResponeStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
	
	return userService.registerUser(userRequest);
	
}
@GetMapping("/test")
public String test() {
    return "Hello From CMS";
}


}
