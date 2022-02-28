package com.appllication.teluslibrary.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateUserDto {
	private String firstName;

	private String lastName;

	private String email;
}
