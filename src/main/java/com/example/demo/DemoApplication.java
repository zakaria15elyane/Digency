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
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		RoleEntity r1=roleRepository.save(new RoleEntity(null,"admin"));
			
			UserEntity u1=new UserEntity();
			u1.setIdUser(null);
			u1.setUsername("admin");
			u1.setPassword(passwordEncoder.encode("admin"));
			u1.getRoles().add(r1);
			userRepository.save(u1);
		
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
