package com.appllication.teluslibrary.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class CreateLoanDto {
	private Long bookId;
	private Long userId;
}
