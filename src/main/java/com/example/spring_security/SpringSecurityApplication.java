package com.example.spring_security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring_security.domain.User;
import com.example.spring_security.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication {
@Autowired
	private UserRepository repo;
	
@PostConstruct	
public void initUsers()
{
	List<User> users = Stream.of(
			 new User(101, "admin", "admin", "test@test.com")).collect(Collectors.toList());
	repo.saveAll(users);
}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
		System.out.println("TEST");
	}

	
}
