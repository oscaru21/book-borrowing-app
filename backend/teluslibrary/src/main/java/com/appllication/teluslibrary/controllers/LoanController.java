package com.appllication.teluslibrary.controllers;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.CreateLoanDto;
import com.appllication.teluslibrary.payload.UpdateLoanDto;
import com.appllication.teluslibrary.services.LoanService;
import com.appllication.teluslibrary.util.LoanStatus;

@RestController
@RequestMapping("/loans")
public class LoanController {
	
	@Autowired
	LoanService ls;
	@Autowired
	ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<LoanDto> createLoan(@RequestBody CreateLoanDto loanDto) {
		return new ResponseEntity<>(mapLoanToDto(ls.createLoan(loanDto)), HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<LoanDto> updateLoan(@RequestBody UpdateLoanDto loanDto) {
		return new ResponseEntity<>(mapLoanToDto(ls.updateLoan(loanDto)), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<LoanDto> getLoan(@PathVariable Long id) {
		return new ResponseEntity<>(mapLoanToDto(ls.getLoan(id)), HttpStatus.OK);
	}
	
	//model mapper methods
	public LoanDto mapLoanToDto(Loan loan) {  
		changeStatus(loan);
		LoanDto loanDto = mapper.map(loan, LoanDto.class);
		return loanDto;
	}
	
	public void changeStatus(Loan loan) {
		Integer days = LocalDate.now().getDayOfYear() - loan.getStartDate().getDayOfYear();
		if(days <= 7) {
			loan.setStatus(LoanStatus.ON_TIME.getValue());
		}else {
			loan.setStatus(LoanStatus.ON_DELAY.getValue());
		}	
	}
}
