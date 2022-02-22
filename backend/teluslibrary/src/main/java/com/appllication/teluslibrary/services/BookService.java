package com.appllication.teluslibrary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	public Book getBook(Long id) {
		return bookRepository.findById(id).get();
	}
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}
