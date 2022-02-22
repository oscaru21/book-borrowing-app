/*
package com.appllication.teluslibrary.Service;

import com.appllication.teluslibrary.Repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book get(long userId) {
        return repository.;
    }

    public List<User> list() {
        Iterable<User> users = repository.findAll();
        List<User> list = new ArrayList<User>();
        users.forEach(list::add);
        return list;
    }

    public User create(User user) {
        return repository.save(user);
    }
}
*/
