package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {

	@Query("SELECT u FROM UserEntity u  WHERE u.username = :username")
	UserEntity findUserByUsername(@Param("username") String username);
}
