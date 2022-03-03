package com.appllication.teluslibrary.loan;

import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDate;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appllication.teluslibrary.TeluslibraryApplication;
import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.repositories.LoanRepository;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TeluslibraryApplication.class)
public class LoanRepositoryTest {
	@Autowired
	LoanRepository loanRepository;
	
	@Test
	@DisplayName("test should pass if we receive a Loan with same id")
	public void findByIdTest() {
		//with
		Long loanId = 1L;
		//when
		Loan loan = loanRepository.findById(loanId).orElseGet(()->null);
		//then
		MatcherAssert.assertThat(loan.getCorrelative(), equalTo(loanId));
	}
	
	@Test
	@DisplayName("test if objects is inserted and returned")
	public void saveTest() {
		//with
		
		Loan loan = new Loan();
		Book book = new Book();
		book.setId(1L);
		User user = new User();
		user.setId(1L);
		loan.setBook(book);
		loan.setUser(user);
		loan.setStartDate(LocalDate.now());
		loan.setStatus("on time");
		loan.setType("borrowing");
		//when
		Loan loanResult = loanRepository.save(loan);
		//then
		MatcherAssert.assertThat(loanResult, equalTo(loan));
	}
}
