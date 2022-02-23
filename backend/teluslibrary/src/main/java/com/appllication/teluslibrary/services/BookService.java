package com.appllication.teluslibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appllication.teluslibrary.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	


}
