package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class CreateUserDto {
	private String firstName;

	private String lastName;

	private String email;
}
