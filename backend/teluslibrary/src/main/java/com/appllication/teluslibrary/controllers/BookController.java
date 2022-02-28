package com.appllication.teluslibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.payload.CreateBookDto;
import com.appllication.teluslibrary.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
		@Autowired
		BookService bs;
		
		//Get all books
		@GetMapping("")
		public List<Book>getAllBooks(){
			return bs.getBook();
		}
		
		//Create book
		@PostMapping("")
		public Book createBook(@RequestBody CreateBookDto bookDto) {
			return bs.createBook(bookDto);
		}
		
		//Get Book by Id
		@GetMapping("/{id}")
		public ResponseEntity<Book> getBook(@PathVariable Long id){
			try {
				Book book = bs.getBook(id);
				return new ResponseEntity<>(book, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		//Get Book by Title
		@GetMapping("/searchBy/{title}")
		public ResponseEntity<Book> getBookByTitle(@PathVariable String title){
			try {
				Book book = bs.getBook(title);
				return new ResponseEntity<>(book, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
}
