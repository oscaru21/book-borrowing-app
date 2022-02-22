package com.appllication.teluslibrary.Entity;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;


@Entity
@Table(name="BOOK",uniqueConstraints={@UniqueConstraint(columnNames={"title"})})
public class Book 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NonNull
	@JsonProperty("title")
	@Column(name = "title")
	private String title;

	@NonNull
	@JsonProperty("stock")
	@Column(name = "stock")
	private Long stock;

	
	@OneToMany(mappedBy = "fk_book")
    Set<Loan> Loans;
 
}