package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userService.findUserByUsername(username);
		System.out.println(userEntity.toString());
		Collection<GrantedAuthority> roles = new ArrayList<>();
		userEntity.getRoles().forEach(r -> {
			roles.add(new SimpleGrantedAuthority(r.getNomRole()));
		});
		return new User(userEntity.getUsername(), userEntity.getPassword(), roles);
	} }


