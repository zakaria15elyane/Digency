package com.example.demo.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
public class JwtAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
		if (request.getMethod().equals("/login ")) {
			filterChain.doFilter(request, response);
		} else if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		} else {
			String jwtToken = request.getHeader("Authorization");
			if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
				try {
					String jwt = jwtToken.substring(7);
					Algorithm algorithm = Algorithm.HMAC256("MySecret1234");
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					Collection<GrantedAuthority> authorities = new ArrayList<>();
					for (String role : roles) {
						authorities.add(new SimpleGrantedAuthority(role));
					}
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {
					response.setHeader("error-message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		}

	}

}
