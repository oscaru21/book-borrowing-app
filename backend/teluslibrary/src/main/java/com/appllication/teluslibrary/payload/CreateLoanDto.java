package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class createLoanDto {
	private Long bookId;
	private Long userId;
}
