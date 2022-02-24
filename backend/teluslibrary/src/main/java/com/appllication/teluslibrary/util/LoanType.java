package com.appllication.teluslibrary.util;

import lombok.Getter;

@Getter
public enum LoanType {
	RENEWAL("renewal"),
	BORROWING("borrowing");
	
	private String value;
	
	LoanType(String value) {
		this.value = value;
	}

}
