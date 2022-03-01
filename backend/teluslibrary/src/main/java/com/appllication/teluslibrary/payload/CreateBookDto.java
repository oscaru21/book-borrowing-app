package com.appllication.teluslibrary.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class CreateBookDto {
	private String title;
	private Integer Stock;
}
