package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class BookDto {
	private String title;
	private Long stock;
	private String status;
	
}
