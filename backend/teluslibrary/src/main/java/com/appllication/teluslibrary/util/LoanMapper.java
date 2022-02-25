package com.appllication.teluslibrary.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.payload.LoanDto;

public class LoanMapper {
	
	public static LoanDto mapLoanToDto(Loan loan) {
		LoanDto loanDto = new LoanDto();
		loanDto.setId(loan.getCorrelative());
		loanDto.setBookTitle(loan.getBook().getTitle());
		loanDto.setStartDate(loan.getStartDate());
		loanDto.setPenalty(calculatePenalty(loan));
		loanDto.setStatus(loan.getStatus());
		return loanDto;
	}
	public static Float calculatePenalty(Loan loan) {
		if(loan.getStatus().equals(LoanStatus.RETURNED.getValue())) {
			return 0F;
		}else {
			Integer days = LocalDate.now().getDayOfYear() - loan.getStartDate().getDayOfYear();
			if(days <= 7) {
				days = 0;
				loan.setStatus(LoanStatus.ON_TIME.getValue());
			}else {
				days = days - 7;
				loan.setStatus(LoanStatus.ON_DELAY.getValue());
			}
			return 0.2F * days;
		}
	}
	public static Integer getActiveLoans(List<Loan> loans) {
		return loans.stream().filter(el -> !el.getStatus()
				.equals(LoanStatus.RETURNED.getValue()))
				.collect(Collectors.toList()).size();
	}
}
