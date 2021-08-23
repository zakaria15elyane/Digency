package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;

public class UserDto {

	private Long idUser;
	private String username;
	private String password;
	private List<RoleDto> roleDto;

	
	
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleDto> getRoleDto() {
		return roleDto;
	}
	public void setRoleDto(List<RoleDto> roleDto) {
		this.roleDto = roleDto;
	}
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Long idUser, String username, String password, List<RoleDto> roleDto) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.roleDto = roleDto;
	}
	public static UserDto entityToDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		if (userEntity != null) {
			userDto.setIdUser(userEntity.getIdUser());
			userDto.setUsername(userEntity.getUsername());
			userDto.setPassword(userEntity.getPassword());
			userDto.setRoleDto(RoleDto.entitiesToDtos(userEntity.getRoles()));
		
			
		}
		return userDto;
	}
	public static List<UserDto> entitiesToDtos(List<UserEntity> listUserentity) {
		List<UserDto> listUserDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listUserentity)) {
			for (UserEntity entity : listUserentity) {
				listUserDto.add(entityToDto(entity));
		
			}
		}
		return listUserDto;
	}
	
	public static UserEntity dtoToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		if (userDto != null) {
			userEntity.setIdUser(userDto.getIdUser());
			userEntity.setUsername(userDto.getUsername());
			userEntity.setPassword(userDto.getPassword());
			
		}
		return userEntity;
	}

	/**
	 * 
	 * @param listFormationDto
	 * @return dtosToEntities
	 */
	public static List<UserEntity> dtosToEntities(List<UserDto> listUserDto) {
		List<UserEntity> listUserEntity = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listUserDto)) {
			for (UserDto dto : listUserDto) {
				listUserEntity.add(dtoToEntity(dto));
			}
		}
		return listUserEntity;
	}
	
	
	
	
}
