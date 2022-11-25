package com.iset.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iset.dao.UserRepository;

import com.iset.entities.User;
import com.iset.service.UserService;

public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public List <User> getusers(){
	return  userRepository.findAll();
	}
	@PostMapping("/login")
	public User saveUser(@RequestBody User r) {
	return userService.saveUser(r.getUsername(), r.getPassword(), r.getPassword());
	}
	

}
