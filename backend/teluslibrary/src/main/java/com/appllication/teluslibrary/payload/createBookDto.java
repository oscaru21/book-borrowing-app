package com.appllication.teluslibrary.payload;

import lombok.Data;

@Data
public class createBookDto {
	private String title;
	private Integer Stock;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getStock() {
		return Stock;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	
}
