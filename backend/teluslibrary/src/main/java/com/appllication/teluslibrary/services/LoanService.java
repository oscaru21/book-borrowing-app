package com.appllication.teluslibrary.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.exceptions.LibraryAPIException;
import com.appllication.teluslibrary.exceptions.ResourceNotFoundException;
import com.appllication.teluslibrary.payload.CreateLoanDto;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.UpdateLoanDto;
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
	@Autowired
	ModelMapper mapper;
	
	
    
	public LoanDto createLoan(CreateLoanDto loanDto) {
		User user = userRepository
				.findById(loanDto.getUserId())
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", loanDto.getUserId().toString() ));
		Book book = bookRepository
				.findById(loanDto.getBookId())
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", loanDto.getBookId().toString() ));
		
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
			return mapLoanToDto(loanRepository.save(loan));
		}else {
			throw new LibraryAPIException("User can't have more than 3 books or book stock is 0");
		}
		
	}
	
	public LoanDto updateLoan(UpdateLoanDto loanDto) {
		Loan loan = loanRepository.findById(loanDto
				.getLoanId())
				.orElseThrow(()-> new ResourceNotFoundException("Loan", "id", loanDto.getLoanId().toString()));
		switch (loanDto.getOperation().toLowerCase()) {
		case "renew":
			return mapLoanToDto(renewBook(loan));

		case "return":
			return mapLoanToDto(returnBook(loan));

		default:
			throw new LibraryAPIException("Operation not recognized");
		}
	}

	public LoanDto getLoan(Long id) {
		return mapLoanToDto(loanRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Loan", "id", id.toString())));
	}
	
	public Float checkPenalties(Loan loan) {
		Integer days = LocalDate.now().getDayOfYear() - loan.getStartDate().getDayOfYear();
		return 0.2F * days;
	}
	
	private Loan returnBook(Loan loan) {		
		Book book = loan.getBook();
		//updates book stock
		book.setStock(book.getStock() + 1);
		bookRepository.save(book);
		loan.setStatus(LoanStatus.RETURNED.getValue());
		return loanRepository.save(loan);
	}
	
	private Loan renewBook(Loan loan) {
		
		loan.setStartDate(LocalDate.now());
		loan.setStatus(LoanStatus.ON_TIME.getValue());
		loan.setType(LoanType.RENEWAL.getValue());
		return loanRepository.save(loan);
		
	}
	
	private Boolean checkStock(Book book) {
		return book.getStock() > 0;
	}
	
	private Boolean checkLimits(User user) {
		return user.getLoans().stream().filter(el -> !el.getStatus()
				.equals(LoanStatus.RETURNED.getValue()))
				.collect(Collectors.toList()).size() < 3;
	}
	
	public LoanDto mapLoanToDto(Loan loan) {  
		LoanDto loanDto = mapper.map(loan, LoanDto.class);
		loanDto.setId(loan.getCorrelative());
		loanDto.setBookTitle(loan.getBook().getTitle());
		loanDto.setPenalty(calculatePenalty(loan));
		return loanDto;
	}
	
	public Float calculatePenalty(Loan loan) {
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
	
	public Integer getActiveLoans(List<Loan> loans) {
		return loans.stream()
				.filter(el -> !el.getStatus()
				.equals(LoanStatus.RETURNED.getValue()))
				.collect(Collectors.toList()).size();
	}
}
