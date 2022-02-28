package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class CreateLoanDto {
	private Long bookId;
	private Long userId;
}
