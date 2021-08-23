package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.response.DigencyResponse;
import com.example.demo.service.impl.UserService;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/v0") 
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TECHNICIEN')")
	public DigencyResponse getUsers() {
		
		return new DigencyResponse(UserDto.entitiesToDtos(userService.getUser()),HttpStatus.OK);
	}

	@GetMapping(value = "/v0/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TECHNICIEN')")
	public DigencyResponse getUserById(@PathVariable Long id) {
		
		return new DigencyResponse(UserDto.entityToDto(userService.findUserById(id)), HttpStatus.OK);
	}
}