package com.example.spring_security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring_security.service.MyUserDetailsService;
import com.example.spring_security.util.JWTUtil;

@Component
public class JwtFilter extends OncePerRequestFilter{
@Autowired
	private JWTUtil jwtutil;
@Autowired
private MyUserDetailsService service;

	
	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
	String authHeader =	httpRequest.getHeader("Authorization");
	 String token = null;
     String username = null;
	if(authHeader!=null && authHeader.startsWith("Bearer "))
	{
		token = authHeader.substring(7);
		username = jwtutil.extractUsername(token);
	}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails details = service.loadUserByUsername(username);
			if(jwtutil.validateToken(token, details))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		  filterChain.doFilter(httpRequest, httpResponse);
	}

}
