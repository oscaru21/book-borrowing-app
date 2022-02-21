package com.appllication.teluslibrary.Repository;

import org.springframework.data.repository.CrudRepository;
import com.appllication.teluslibrary.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> 
{
	
}
