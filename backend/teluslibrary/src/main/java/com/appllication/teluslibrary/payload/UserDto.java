package com.appllication.teluslibrary.payload;

import java.util.List;

import lombok.Data;
@Data
public class UserDto {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Integer activeLoans;
	
	private List<LoanDto> loans;
}
