package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.exceptions.ResourceNotFoundException;
import com.appllication.teluslibrary.payload.BookDto;
import com.appllication.teluslibrary.payload.CreateBookDto;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.repositories.BookRepository;

@Setter
@Getter
@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	ModelMapper mapper;
	
	public List<BookDto> getBook(){
		List<Book> books = bookRepository.findAll();
		return books.stream().map(t -> mapBookToDto(t)).collect(Collectors.toList());
	}
	
	public BookDto getBook(Long id) {
		return mapBookToDto(bookRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", id.toString())));
	}
	public BookDto getBook(String title) {
		return mapBookToDto(bookRepository
				.findByTitle(title)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", title)));
	}
	
	
	public BookDto createBook(CreateBookDto bookDto) {
		return mapBookToDto(bookRepository.save(mapToEntity(bookDto)));
	}
	
	
	public Book mapToEntity(CreateBookDto bookDto) {
		Book book = mapper.map(bookDto, Book.class);
		return book;		
	}
	
	public BookDto mapBookToDto(Book book) {
		BookDto bookDto = mapper.map(book, BookDto.class);
		return bookDto;
	}
	

}
