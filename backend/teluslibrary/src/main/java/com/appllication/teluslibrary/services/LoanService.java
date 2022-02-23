package com.appllication.teluslibrary.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.payload.createLoanDto;
import com.appllication.teluslibrary.repositories.BookRepository;
import com.appllication.teluslibrary.repositories.LoanRepository;
import com.appllication.teluslibrary.repositories.UserRepository;
import com.appllication.teluslibrary.util.LoanStatus;
import com.appllication.teluslibrary.util.LoanType;

@Service
public class LoanService {
	@Autowired
	LoanRepository loanRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	
	public Loan createLoan(createLoanDto loanDto) {
		User user = userRepository.findById(loanDto.getUserId()).get();
		Book book = bookRepository.findById(loanDto.getBookId()).get();
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setUser(user);
		loan.setStartDate(LocalDate.now());
		loan.setStatus(LoanStatus.ON_TIME);
		loan.setType(LoanType.BORROWING);
		return loanRepository.save(loan);
	}

}
