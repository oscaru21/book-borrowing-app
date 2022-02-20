package com.appllication.teluslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appllication.teluslibrary.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
