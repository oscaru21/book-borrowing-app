package com.appllication.teluslibrary.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class CreateBookDto {
	public Long id;
	public String title;
	public Integer Stock;
}
