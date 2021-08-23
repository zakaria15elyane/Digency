package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserEntity;

public interface IUserService {
public List<UserEntity> getUser();
	
	public UserEntity addUser(UserEntity userEntity);
	
	public UserEntity findUserByUsername(String username);
	
	public UserEntity findUserById(Long id);
}
