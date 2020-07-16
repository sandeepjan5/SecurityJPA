package com.example.spring_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security.domain.AuthRequest;
import com.example.spring_security.util.JWTUtil;

@RestController
public class SecurityController 
{
	@Autowired
	private JWTUtil jwtutil;
	@Autowired
	private AuthenticationManager authMgr;
	@GetMapping("/")
	public String welcome()
	{
		return "Welcome to Security page";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception
	{
		System.out.println("Controller");
		try
		{
		authMgr.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
		}
		catch(Exception e)
		{
			throw new Exception("invalid credentials");
		}
		
		return	jwtutil.generateToken(authRequest.getUserName());
		
	}
	
	
}
