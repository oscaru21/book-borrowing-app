package com.appllication.teluslibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.payload.CreateUserDto;
import com.appllication.teluslibrary.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService us;
	@GetMapping("/users")
	public List<User> getUsers(){
		return us.getUsers();
	}
	@PostMapping("/users")
	public User createUser(@RequestBody CreateUserDto userDto) {
		return us.createUser(userDto);
	}
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		return us.updateUser(user);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		try {
			User user = us.getUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		return us.deleteUser(id);
	}
}
