package com.appllication.teluslibrary.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LoanDto {
	private Long id;
	private String bookTitle;
	private LocalDate startDate;
	private String status;
	private Float penalty;
}
