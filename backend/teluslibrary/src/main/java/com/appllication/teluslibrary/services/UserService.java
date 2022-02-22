package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.payload.CreateUserDto;
import com.appllication.teluslibrary.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User getUser(Long id) {
		Optional<User> findById = userRepository.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			return findById.orElseThrow();
		}
	}
	
	public User createUser(CreateUserDto userDto) {
		return userRepository.save(mapToEntiy(userDto));
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public ResponseEntity<User> deleteUser(Long id) {
		try {
			User user = getUser(id);
			userRepository.deleteById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public User mapToEntiy(CreateUserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());
		return user;
	}
}
