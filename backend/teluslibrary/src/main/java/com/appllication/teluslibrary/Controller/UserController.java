/*
package com.appllication.teluslibrary.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.appllication.teluslibrary.Entity.User;
import com.appllication.teluslibrary.Repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController 
{
	@Autowired
	UserRepository urs;
	@RequestMapping("/") 
	public String hello()
	{
		Long tmp = urs.count();
		User s = new User();
		urs.save(s);
        return "Hola"+tmp;
    }
}
*/
