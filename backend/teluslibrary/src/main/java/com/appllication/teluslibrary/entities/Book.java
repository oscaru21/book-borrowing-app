package com.appllication.teluslibrary.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	private Integer stock;
	private Float penaltyCost;
}
