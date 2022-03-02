package com.appllication.teluslibrary.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appllication.teluslibrary.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	Optional<Book> findByTitle(String title);
}
