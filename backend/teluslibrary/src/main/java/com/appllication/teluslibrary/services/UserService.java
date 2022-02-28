package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.exceptions.ResourceNotFoundException;
import com.appllication.teluslibrary.payload.CreateUserDto;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.UserDto;
import com.appllication.teluslibrary.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoanService loanService;
	@Autowired
	ModelMapper mapper;
	public List<UserDto> getUsers(){
		List<User> users = userRepository.findAll();
		return users.stream().map(el -> mapUserToDto(el)).collect(Collectors.toList());

	}
	
	public UserDto getUser(Long id) {
		return mapUserToDto(userRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", id.toString())));
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
		User user = mapper.map(userDto, User.class);
		return user;
	}
	private UserDto mapUserToDto(User user) {
		UserDto userDto = mapper.map(user, UserDto.class);
		if(user.getLoans() != null) {
			userDto.setActiveLoans(loanService.getActiveLoans(user.getLoans()));
			userDto.setLoans(user.getLoans().stream()
					.map(loan -> loanService.mapLoanToDto(loan))
					.collect(Collectors.toList()));
		}else {
			userDto.setActiveLoans(0);
		}
		return userDto;
	}
	
}
