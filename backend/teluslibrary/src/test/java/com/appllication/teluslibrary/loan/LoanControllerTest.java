package com.appllication.teluslibrary.loan;

import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDate;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appllication.teluslibrary.TeluslibraryApplication;
import com.appllication.teluslibrary.controllers.LoanController;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.payload.CreateLoanDto;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.UpdateLoanDto;
import com.appllication.teluslibrary.services.LoanService;
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TeluslibraryApplication.class)
public class LoanControllerTest {
	@InjectMocks
	LoanController loanController;
	@Mock
	LoanService loanServiceMock;
	@Mock
	ModelMapper mapperMock;
	@Test
	public void createLoanTest() {
		// with
		CreateLoanDto createLoanDto = new CreateLoanDto();
		createLoanDto.setBookId(1L);
		createLoanDto.setUserId(1L);
		Loan loan = new Loan(1L, LocalDate.now(), "borrowing", "on time", null, null);
		LoanDto loanDto = new LoanDto(1L, "Harry Potter", LocalDate.now(), "on time", 0F);
		// when
		Mockito.when(loanServiceMock.createLoan(createLoanDto)).thenReturn(loan);
		Mockito.when(mapperMock.map(loan, LoanDto.class)).thenReturn(loanDto);
		LoanDto resultLoan = loanController.createLoan(createLoanDto).getBody();
		// then
		MatcherAssert.assertThat(resultLoan.getStatus(), equalTo("on time"));
	}
	@Test
	public void updateLoanTest() {
		// with
		UpdateLoanDto updateLoanDto = new UpdateLoanDto();
		updateLoanDto.setLoanId(1L);
		updateLoanDto.setOperation("renew");
		Loan loan = new Loan(1L, LocalDate.now(), "borrowing", "on time", null, null);
		LoanDto loanDto = new LoanDto(1L, "Harry Potter", LocalDate.now(), "on time", 0F);
		// when
		Mockito.when(loanServiceMock.updateLoan(updateLoanDto)).thenReturn(loan);
		Mockito.when(mapperMock.map(loan, LoanDto.class)).thenReturn(loanDto);
		LoanDto resultLoan = loanController.updateLoan(updateLoanDto).getBody();
		// then
		MatcherAssert.assertThat(resultLoan.getStatus(), equalTo("on time"));
	}
	@Test
	public void getLoanTest() {
		// with
		Loan loan = new Loan(1L, LocalDate.now(), "borrowing", "on time", null, null);
		LoanDto loanDto = new LoanDto(1L, "Harry Potter", LocalDate.now(), "on time", 0F);
		// when
		Mockito.when(loanServiceMock.getLoan(1L)).thenReturn(loan);
		Mockito.when(mapperMock.map(loan, LoanDto.class)).thenReturn(loanDto);
		LoanDto resultLoan = loanController.getLoan(1L).getBody();
		// then
		MatcherAssert.assertThat(resultLoan.getStatus(), equalTo("on time"));
	}
	

}
