package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRoleService;
@Service
@Transactional
public class RoleService  implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;
	@Override
	public List<RoleEntity> getAllRole() {
		List<RoleEntity> roles = null;
		try {
			roles = roleRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return roles;
	}

}
