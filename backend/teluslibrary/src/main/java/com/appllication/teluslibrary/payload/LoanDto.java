package com.appllication.teluslibrary.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	private Long id;
	private String bookTitle;
	private LocalDate startDate;
	private String status;
	private Float penalty;
}
