package com.appllication.teluslibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.payload.createLoanDto;
import com.appllication.teluslibrary.payload.updateLoanDto;
import com.appllication.teluslibrary.services.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {
	
	@Autowired
	LoanService ls;
	
	@PostMapping
	public Loan createLoan(@RequestBody createLoanDto loanDto) {
		return ls.createLoan(loanDto);
	}
	@PutMapping
	public Loan updateLoan(@RequestBody updateLoanDto loanDto) {
		return ls.updateLoan(loanDto);
	}
	@GetMapping("/{id}")
	public Loan getLoan(@PathVariable Long id) {
		return ls.getLoan(id);
	}
}
