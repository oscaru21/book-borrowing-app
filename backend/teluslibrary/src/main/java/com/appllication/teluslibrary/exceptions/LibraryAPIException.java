package com.appllication.teluslibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LibraryAPIException extends RuntimeException {
	private String message;

	public LibraryAPIException(String message) {
		super(message);
		this.message = message;
	}

}
