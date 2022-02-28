package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.exceptions.ResourceNotFoundException;
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
	
	public List<Book> getBook(){
		return bookRepository.findAll();
	}
	
	public Book getBook(Long id) {
		return bookRepository
				.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", id.toString()));
	}
	public Book getBook(String title) {
		return bookRepository
				.findByTitle(title)
				.orElseThrow(()-> new ResourceNotFoundException("Book", "id", title));
	}
	
	public Book createBook(CreateBookDto bookDto) {
		return bookRepository.save(mapToEntity(bookDto));
	}
		
	public Book mapToEntity(CreateBookDto bookDto) {
		Book book = mapper.map(bookDto, Book.class);
		return book;		
	}
	

}
