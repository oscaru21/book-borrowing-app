package com.appllication.teluslibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.services.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bs;
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bs.getBooks();
	}
	@GetMapping("/books/{id}")
	public Book getBook(@PathVariable Integer id){
		return bs.getBook(id);
	}
	
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return bs.createBook(book);
	}
	
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		return bs.updateBook(book);
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable Integer id){
		bs.deleteBook(id);
	}
}
