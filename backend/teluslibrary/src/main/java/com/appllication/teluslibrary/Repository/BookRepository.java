package com.appllication.teluslibrary.Repository;

import org.springframework.data.repository.CrudRepository;
import com.appllication.teluslibrary.Entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> 
{
	
}
