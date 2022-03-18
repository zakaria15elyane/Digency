package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.UserDto;
import com.example.demo.response.DigencyResponse;
import com.example.demo.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/v0") 
	@PreAuthorize("hasAuthority('admin') or hasAuthority('TECHNICIEN')")
	public DigencyResponse getUsers() {
		
		return new DigencyResponse(UserDto.entitiesToDtos(userService.getUser()),HttpStatus.OK);
	}

	@GetMapping(value = "/v0/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('TECHNICIEN')")
	public DigencyResponse getUserById(@PathVariable Long id) {
		
		return new DigencyResponse(UserDto.entityToDto(userService.findUserById(id)), HttpStatus.OK);
	}
	@GetMapping(value = "/refreshToken")

	public void refreshToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*if(request.getServletPath().equals("refreshToken")) {
			chain.doFilter(request, response);
		}else {*/
			String authToken = request.getHeader("authorization");
			if (authToken != null && authToken.startsWith("Bearer ")) {
				try {
					String jwt = authToken.substring(7);
					Algorithm algorithm = Algorithm.HMAC256("MySecret1234");
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					String username = decodedJWT.getSubject();
					com.example.demo.entity.UserEntity userEntity = userService.findUserByUsername(username);

					String jwtAcessToken = JWT.create().withSubject(userEntity.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
							.withIssuer(request.getRequestURL().toString())
							.withClaim("roles", userEntity
									.getRoles().stream().map(r -> r.getNomRole()).collect(Collectors.toList()))
							.sign(algorithm);

					java.util.Map<String, String> idToken = new HashMap<>();
					idToken.put("jwt-acess-token", jwtAcessToken);
					idToken.put("jwt-refresh-token", jwt);
					response.setContentType("application/json");
					new ObjectMapper().writeValue(response.getOutputStream(), idToken);
				} catch (Exception e) {
					response.setHeader("error-message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}

			} else {
				throw new RuntimeException("Refresh Token requide");
			}
		

	
	
	}
}