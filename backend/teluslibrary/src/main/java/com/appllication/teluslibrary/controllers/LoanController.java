package com.appllication.teluslibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.payload.createLoanDto;
import com.appllication.teluslibrary.services.LoanService;

@RestController
public class LoanController {
	@Autowired
	LoanService ls;
	@PostMapping("/loans")
	public Loan createLoan(@RequestBody createLoanDto loanDto) {
		return ls.createLoan(loanDto);
	}
}
