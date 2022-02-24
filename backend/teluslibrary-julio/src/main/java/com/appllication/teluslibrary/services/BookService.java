package com.appllication.teluslibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.payload.createBookDto;
import com.appllication.teluslibrary.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBook(){
		return bookRepository.findAll();
	}
	
	public Book getBook(Long id) {
		Optional<Book> findById = bookRepository.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			return findById.orElseThrow();
		}
	}
	public Book getBook(String title) {
		Optional<Book> findByTitle = Optional.of(bookRepository.findByTitle(title));
		if(findByTitle.isPresent()) {
			return findByTitle.get();
		}else {
			return findByTitle.orElseThrow();
		}
	}
	
	public Book createBook(createBookDto bookDto) {
		return bookRepository.save(mapToEntity(bookDto));
	}
		
	public Book mapToEntity(createBookDto bookDto) {
		Book book = new Book();
		book.setTitle(bookDto.getTitle());
		book.setStock(bookDto.getStock());
		return book;		
	}
	

}
