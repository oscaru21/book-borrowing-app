package com.appllication.teluslibrary.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
	private Date date;
	private String message;
	private String description;
}
