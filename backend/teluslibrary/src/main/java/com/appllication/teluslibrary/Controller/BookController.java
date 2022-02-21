
package com.appllication.teluslibrary.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




import com.appllication.teluslibrary.Entity.Book;
import com.appllication.teluslibrary.Repository.BookRepository;

@RestController
public class BookController {


    @Autowired
	private BookRepository bookrepo;

    /*
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Book> list() {
        List<User> users = userService.list();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> userById(@RequestParam(value = "id") long id) {
        User user = userService.get(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    */


	@RequestMapping("/") 
	public String hello()
	{
        return "Hola";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book book1 = bookrepo.save(book);
        return new ResponseEntity(book1, HttpStatus.CREATED);
    }
}
