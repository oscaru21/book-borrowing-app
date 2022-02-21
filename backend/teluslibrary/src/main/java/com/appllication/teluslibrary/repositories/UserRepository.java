package com.appllication.teluslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appllication.teluslibrary.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
