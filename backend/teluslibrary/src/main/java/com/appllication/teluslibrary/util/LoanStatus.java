package com.appllication.teluslibrary.util;

import lombok.Getter;

@Getter
public enum LoanStatus {
	RETURNED("returned"),
	ON_DELAY("on delay"),
	ON_TIME("on time");
	
	private String value;
	
	LoanStatus(String value) {
		this.value = value;
	}
}
