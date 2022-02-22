package com.appllication.teluslibrary.payload;

import com.appllication.teluslibrary.util.UserRole;

import lombok.Data;

@Data
public class CreateUserDto {
	private String firstName;

	private String lastName;

	private String email;

	private UserRole role;
}
