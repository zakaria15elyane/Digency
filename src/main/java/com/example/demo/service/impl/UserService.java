package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;
@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository; 
	
	@Override
	public List<UserEntity> getUser() {
		List<UserEntity> users=null;
		try {
			users = userRepository.findAll();
		} catch (Exception e) {
			
		}
		return users;
	}

	@Override
	public UserEntity addUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findUserByUsername(String username) {
		UserEntity user=null;
	
		try {
			user = userRepository.findUserByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
		}

	@Override
	public UserEntity findUserById(Long id) {
		UserEntity user = null;
		try {
			user = userRepository.findById(id).get();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

}
