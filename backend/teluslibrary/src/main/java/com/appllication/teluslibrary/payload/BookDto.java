package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class BookDto {
	private Long id;
	private String title;
	private Integer stock;
}
