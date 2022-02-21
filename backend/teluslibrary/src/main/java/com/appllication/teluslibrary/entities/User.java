package com.appllication.teluslibrary.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	private String nbooks;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
	private List<Loan> loans;
}
