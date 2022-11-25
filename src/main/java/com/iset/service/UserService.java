package com.iset.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iset.dao.UserRepository;
import com.iset.entities.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
private final UserRepository userRepository;
@Autowired
public UserService(UserRepository userRepository) {
this.userRepository = userRepository;
}
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Objects.requireNonNull(username);
User user = userRepository.findUserWithName(username)
.orElseThrow(() -> new UsernameNotFoundException("User not found"));
return user;
}

public User saveUser(String username, String password, String confirmedPassword) {
User appUser = new User();
if (userRepository.findUserWithName(username).isPresent() == true)
throw new RuntimeException("User already exists");
if (!password.equals(confirmedPassword))
throw new RuntimeException("Please confirm your password");
appUser.setUsername(username);
userRepository.save(appUser);
return appUser;
} 
}

