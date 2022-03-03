package com.appllication.teluslibrary.loan;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appllication.teluslibrary.TeluslibraryApplication;
import com.appllication.teluslibrary.controllers.LoanController;
import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.exceptions.LibraryAPIException;
import com.appllication.teluslibrary.payload.BookDto;
import com.appllication.teluslibrary.payload.CreateLoanDto;
import com.appllication.teluslibrary.payload.UpdateLoanDto;
import com.appllication.teluslibrary.repositories.BookRepository;
import com.appllication.teluslibrary.repositories.LoanRepository;
import com.appllication.teluslibrary.repositories.UserRepository;
import com.appllication.teluslibrary.services.BookService;
import com.appllication.teluslibrary.services.LoanService;

import ch.qos.logback.core.boolex.Matcher;



@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TeluslibraryApplication.class)
public class LoanServiceTest {
	@InjectMocks
	LoanService loanService;
	@Mock
	LoanRepository loanRepositoryMock;
	@Mock
	UserRepository userRepositoryMock;
	@Mock
	BookRepository bookRepositoryMock;
	@Test
	@DisplayName("Create valid loan should return a loan")
	public void createValidLoanTest() {
		//with
		CreateLoanDto loanDto= new CreateLoanDto();
		loanDto.setBookId(1L);
		loanDto.setUserId(1L);
		List<Loan> loans = new ArrayList<Loan>();
		loans.add(new Loan(1L, LocalDate.now(), "on time"));
		loans.add(new Loan(2L, LocalDate.now(), "returned"));
		loans.add(new Loan(3L, LocalDate.now(), "on time"));
		Optional<User> user = Optional.of(new User(1L, "oscar", "umaña", "oscarumana@mail.com", loans));
		Optional<Book> book = Optional.of(new Book(1L, "Harry Potter", 15, new ArrayList<Loan>()));
		//when
		Mockito.when(userRepositoryMock.findById(1L)).thenReturn(user);
		Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(book);
		Mockito.when(bookRepositoryMock.save(book.get())).thenReturn(book.get());
		Mockito.when(loanRepositoryMock.save(Mockito.any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);
		//then
		Loan result = loanService.createLoan(loanDto);
		Assert.assertEquals("borrowing", result.getType());
		
	}
	@Test
	@DisplayName("Create loan with user having more than 3 active loans should throw an exception")
	public void createLoanWithInvalidUser() {
		//with
		CreateLoanDto loanDto= new CreateLoanDto();
		loanDto.setBookId(1L);
		loanDto.setUserId(1L);
		List<Loan> loans = new ArrayList<Loan>();
		loans.add(new Loan(1L, LocalDate.now(), "on time"));
		loans.add(new Loan(2L, LocalDate.now(), "on time"));
		loans.add(new Loan(3L, LocalDate.now(), "on time"));
		Optional<User> user = Optional.of(new User(1L, "oscar", "umaña", "oscarumana@mail.com", loans));
		Optional<Book> book = Optional.of(new Book(1L, "Harry Potter", 15, new ArrayList<Loan>()));
		//when
		Mockito.when(userRepositoryMock.findById(1L)).thenReturn(user);
		Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(book);
		Mockito.when(bookRepositoryMock.save(book.get())).thenReturn(book.get());
		//then
		Exception exception = Assert.assertThrows(LibraryAPIException.class, () -> loanService.createLoan(loanDto));
		Assert.assertEquals("User can't have more than 3 books", exception.getMessage());
	}
	@Test
	@DisplayName("Create loan with an unavailable stock should throw an exception")
	public void createLoanWithUnavailableBook() {
		//with
		CreateLoanDto loanDto= new CreateLoanDto();
		loanDto.setBookId(1L);
		loanDto.setUserId(1L);
		List<Loan> loans = new ArrayList<Loan>();
		loans.add(new Loan(1L, LocalDate.now(), "on time"));
		loans.add(new Loan(2L, LocalDate.now(), "returned"));
		loans.add(new Loan(3L, LocalDate.now(), "on time"));
		Optional<User> user = Optional.of(new User(1L, "oscar", "umaña", "oscarumana@mail.com", loans));
		Optional<Book> book = Optional.of(new Book(1L, "Harry Potter", 0, new ArrayList<Loan>()));
		//when
		Mockito.when(userRepositoryMock.findById(1L)).thenReturn(user);
		Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(book);
		Mockito.when(bookRepositoryMock.save(book.get())).thenReturn(book.get());
		//then
		Exception exception = Assert.assertThrows(LibraryAPIException.class, () -> loanService.createLoan(loanDto));
		Assert.assertEquals("Book is unavailable in stock", exception.getMessage());
	}
	@Test
	@DisplayName("Create loan with an unavailable stock and user that has already 3 active loans should throw an exception")
	public void createLoanWithUnavailableBookandInvalidUser() {
		//with
		CreateLoanDto loanDto= new CreateLoanDto();
		loanDto.setBookId(1L);
		loanDto.setUserId(1L);
		List<Loan> loans = new ArrayList<Loan>();
		loans.add(new Loan(1L, LocalDate.now(), "on time"));
		loans.add(new Loan(2L, LocalDate.now(), "on time"));
		loans.add(new Loan(3L, LocalDate.now(), "on time"));
		Optional<User> user = Optional.of(new User(1L, "oscar", "umaña", "oscarumana@mail.com", loans));
		Optional<Book> book = Optional.of(new Book(1L, "Harry Potter", 0, new ArrayList<Loan>()));
		//when
		Mockito.when(userRepositoryMock.findById(1L)).thenReturn(user);
		Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(book);
		Mockito.when(bookRepositoryMock.save(book.get())).thenReturn(book.get());
		
		//then
		Exception exception = Assert.assertThrows(LibraryAPIException.class, () -> loanService.createLoan(loanDto));
		Assert.assertEquals("Book is unavailable in stock and user has more than 3 books", exception.getMessage());
	}
	@Test
	@DisplayName("Test should pass if the operation is not valid")
	public void notValidUpdateLoanTest() {
		//with
		Optional<Loan> loan = Optional.of(new Loan(1L, LocalDate.now(), "on time"));
		UpdateLoanDto loanDto = new UpdateLoanDto();
		loanDto.setLoanId(1L);
		loanDto.setOperation("borrow"); //not valid operation
		//when
		Mockito.when(loanRepositoryMock.findById(1L)).thenReturn(loan);
		//then
		Exception exception = Assert.assertThrows(LibraryAPIException.class, () -> loanService.updateLoan(loanDto));
		Assert.assertEquals("Operation not recognized", exception.getMessage());
	}
	@Test
	@DisplayName("Test should pass if the operation is renew")
	public void renewUpdateLoanTest() {
		//with
		Optional<Loan> loan = Optional.of(new Loan(1L, LocalDate.now(), "on time"));
		UpdateLoanDto loanDto = new UpdateLoanDto();
		loanDto.setLoanId(1L);
		loanDto.setOperation("renew"); //renew operation
		//when
		Mockito.when(loanRepositoryMock.findById(1L)).thenReturn(loan);
		Mockito.when(loanRepositoryMock.save(Mockito.any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);
		//then
		Loan result = loanService.updateLoan(loanDto);
		Assert.assertEquals("renewal", result.getType());
	}
	@Test
	@DisplayName("Test should pass if the operation is return")
	public void returnUpdateLoanTest() {
		//with
		Book book = new Book(1L, "Harry Potter", 0, new ArrayList<Loan>());
		Optional<Loan> loan = Optional.of(new Loan(1L, LocalDate.now(), "borrowing", "on time", null, book));
		UpdateLoanDto loanDto = new UpdateLoanDto();
		loanDto.setLoanId(1L);
		loanDto.setOperation("return"); //return operation
		//when
		Mockito.when(loanRepositoryMock.findById(1L)).thenReturn(loan);
		Mockito.when(loanRepositoryMock.save(Mockito.any(Loan.class))).thenAnswer(i -> i.getArguments()[0]);
		Mockito.when(bookRepositoryMock.save(Mockito.any(Book.class))).thenAnswer(i -> i.getArguments()[0]);
		//then
		Loan result = loanService.updateLoan(loanDto);
		Assert.assertEquals("returned", result.getStatus());
	}
	@Test
	void getLoanTest() {
		//with
		Optional<Loan> loan = Optional.of(new Loan(1L, LocalDate.now(), "borrowing", "on time", null, null));
		//when
		Mockito.when(loanRepositoryMock.findById(1L)).thenReturn(loan);
        //then
		Loan result = loanService.getLoan(1L);
        MatcherAssert.assertThat(result.getCorrelative(), equalTo(1L));
	}
}
