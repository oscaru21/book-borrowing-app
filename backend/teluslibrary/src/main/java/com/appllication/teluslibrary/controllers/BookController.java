package com.appllication.teluslibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appllication.teluslibrary.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookService bs;
	

	
}
