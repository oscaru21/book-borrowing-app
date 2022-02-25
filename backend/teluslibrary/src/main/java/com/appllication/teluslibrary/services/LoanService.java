package com.appllication.teluslibrary.services;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.exceptions.ResourceNotFoundException;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.createLoanDto;
import com.appllication.teluslibrary.payload.updateLoanDto;
import com.appllication.teluslibrary.repositories.BookRepository;
import com.appllication.teluslibrary.repositories.LoanRepository;
import com.appllication.teluslibrary.repositories.UserRepository;
import com.appllication.teluslibrary.util.LoanMapper;
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
	
	public LoanDto createLoan(createLoanDto loanDto) {
		User user = userRepository.findById(loanDto.getUserId()).get();
		Book book = bookRepository.findById(loanDto.getBookId()).get();
		
		if(checkLimits(user) && checkStock(book)) {
			Loan loan = new Loan();
			loan.setBook(book);
			loan.setUser(user);
			loan.setStartDate(LocalDate.now());
			loan.setStatus(LoanStatus.ON_TIME.getValue());
			loan.setType(LoanType.BORROWING.getValue());
			//updates book stock
			book.setStock(book.getStock() - 1);
			bookRepository.save(book);
			
			return LoanMapper.mapLoanToDto(loanRepository.save(loan));
		}else {
			System.out.println("holi");
			return null;
		}
		
	}
	
	public LoanDto updateLoan(updateLoanDto loanDto) {
		Loan loan = loanRepository.getById(loanDto.getLoanId());
		switch (loanDto.getOperation()) {
		case "renew":
			return LoanMapper.mapLoanToDto(renewBook(loan));

		case "return":
			return LoanMapper.mapLoanToDto(returnBook(loan));

		default:
			return LoanMapper.mapLoanToDto(loan);
		}
	}

	public LoanDto getLoan(Long id) {
		return LoanMapper.mapLoanToDto(loanRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Loan", "id", id.toString())));
	}
	
	public Float checkPenalties(Loan loan) {
		Integer days = LocalDate.now().getDayOfYear() - loan.getStartDate().getDayOfYear();
		return 0.2F * days;
	}
	
	private Loan returnBook(Loan loan) {		
		Book book = loan.getBook();
		if(checkPenalties(loan) == 0){
			//updates book stock
			book.setStock(book.getStock() + 1);
			bookRepository.save(book);
			loan.setStatus(LoanStatus.RETURNED.getValue());
			return loanRepository.save(loan);
		}else {
			return loan;
		}
	}
	
	private Loan renewBook(Loan loan) {
		
		if(checkPenalties(loan) == 0){
			loan.setStartDate(LocalDate.now());
			loan.setStatus(LoanStatus.ON_TIME.getValue());
			loan.setType(LoanType.RENEWAL.getValue());
			return loanRepository.save(loan);
		}else {
			return loan;
		}
	}
	
	private Boolean checkStock(Book book) {
		return book.getStock() > 0;
	}
	
	private Boolean checkLimits(User user) {
		return user.getLoans().stream().filter(el -> !el.getStatus()
				.equals(LoanStatus.RETURNED.getValue()))
				.collect(Collectors.toList()).size() < 3;
	}
}
