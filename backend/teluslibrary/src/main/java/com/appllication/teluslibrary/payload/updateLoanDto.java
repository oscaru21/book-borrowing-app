package com.appllication.teluslibrary.payload;

import lombok.Getter;

@Getter
public class UpdateLoanDto {
	private Long loanId;
	private String operation;

}
