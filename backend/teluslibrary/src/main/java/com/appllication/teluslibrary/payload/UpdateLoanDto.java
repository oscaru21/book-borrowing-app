package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class UpdateLoanDto {
	private Long loanId;
	private String operation;

}
