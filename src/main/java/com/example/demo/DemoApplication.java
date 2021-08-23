package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Autowired(required = true)
	private IUserRepository userRepository;
	
	@Autowired(required = true)
	private IRoleRepository roleRepository;
	
	UserEntity u1=new UserEntity();

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * u1.setIdUser(null); u1.setUsername("admin");
		 * u1.setPassword(passwordEncoder.encode("admin"));
		 */
		
		
		//RoleEntity R1 = roleRepository.save(new RoleEntity(null, "ADMIN"));
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
