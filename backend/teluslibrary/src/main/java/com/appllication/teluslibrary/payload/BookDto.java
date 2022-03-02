package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class BookDto {
	public Long id;
	public String title;
	public Integer stock;
}
