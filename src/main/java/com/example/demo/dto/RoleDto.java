package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;

public class RoleDto {

	
	private Long idRole;


	private String nomRole;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public static RoleDto entityToDto(RoleEntity roleEntity) {
		RoleDto roleDto = new RoleDto();
		if (roleEntity != null) {
			roleDto.setIdRole(roleEntity.getIdRole());
			roleDto.setNomRole(roleEntity.getNomRole());
			
		}
		return roleDto;
	}
	public static List<RoleDto> entitiesToDtos(List<RoleEntity> listRoleentity) {
		List<RoleDto> listRoleDto = new ArrayList<>();
		if (!CollectionUtils.isEmpty(listRoleentity)) {
			for (RoleEntity entity : listRoleentity) {
				listRoleDto.add(entityToDto(entity));
				// System.out.println(listFormationDto);
			}
		}
		return listRoleDto;
	}
	
	public static RoleEntity dtoToEntity(RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		if (roleDto != null) {
			roleEntity.setIdRole(roleDto.getIdRole());
			roleEntity.setNomRole(roleDto.getNomRole());
			
		}
		return roleEntity;
	}
	
}
