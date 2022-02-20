package com.appllication.teluslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appllication.teluslibrary.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
