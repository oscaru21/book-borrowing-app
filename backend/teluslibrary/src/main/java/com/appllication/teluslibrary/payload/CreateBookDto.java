package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class CreateBookDto {
	private String title;
	private Integer Stock;
}
