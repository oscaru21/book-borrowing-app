package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.payload.CreateUserDto;
import com.appllication.teluslibrary.payload.UserDto;
import com.appllication.teluslibrary.repositories.UserRepository;
import com.appllication.teluslibrary.util.LoanMapper;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<UserDto> getUsers(){
		List<User> users = userRepository.findAll();
		return users.stream().map(el -> mapUserToDto(el)).collect(Collectors.toList());

	}
	
	public UserDto getUser(Long id) {
		Optional<User> findById = userRepository.findById(id);
		if(findById.isPresent()) {
			return mapUserToDto(findById.get());
		}else {
			return mapUserToDto(findById.orElseThrow());
		}
	}
	
	public UserDto createUser(CreateUserDto userDto) {
		return mapUserToDto(userRepository.save(mapToEntiy(userDto)));
	}
	
	public UserDto updateUser(User user) {
		return mapUserToDto(userRepository.save(user));
	}
	
	public ResponseEntity<String> deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private User mapToEntiy(CreateUserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		return user;
	}
	private UserDto mapUserToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		if(user.getLoans() != null) {
			userDto.setActiveLoans(LoanMapper.getActiveLoans(user.getLoans()));
			userDto.setLoans(user.getLoans().stream()
					.map(loan -> LoanMapper.mapLoanToDto(loan))
					.collect(Collectors.toList()));
		}else {
			userDto.setActiveLoans(0);
		}
		return userDto;
	}
	
}
