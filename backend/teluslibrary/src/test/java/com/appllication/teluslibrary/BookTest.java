package com.appllication.teluslibrary;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.appllication.teluslibrary.controllers.BookController;
import com.appllication.teluslibrary.entities.Book;
import com.appllication.teluslibrary.payload.createBookDto;
import com.appllication.teluslibrary.repositories.BookRepository;
import com.appllication.teluslibrary.services.BookService;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
import com.telus.ds.DsMyTrackApiApplication;
import com.telus.ds.controller.TrackController;
import com.telus.ds.entity.Album;
import com.telus.ds.entity.Track;
import com.telus.ds.entity.dto.TrackDTO;
import com.telus.ds.repository.AlbumRepository;
import com.telus.ds.repository.TrackRepository;
import com.telus.ds.service.TrackService;
*/

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TeluslibraryApplication.class)

public class BookTest {

    @Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookController bookController;

    @Test
	void getBookController() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("TestBook");
        book.setStock(1);

        BookService bookServiceMock = mock(BookService.class);
        Mockito.when(bookServiceMock.getBook("TestBook")).thenReturn(book);

        Book tmp_book = bookController.getBook(1L).getBody();

        MatcherAssert.assertThat(tmp_book.getTitle(), equalTo("TestBook"));
	}
}
