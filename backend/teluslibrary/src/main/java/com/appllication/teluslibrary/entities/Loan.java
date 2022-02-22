package com.appllication.teluslibrary.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.appllication.teluslibrary.util.LoanStatus;
import com.appllication.teluslibrary.util.LoanType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "loan")
@JsonIgnoreProperties({ "book", "person" })
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private LoanType type;
	
	@Enumerated(EnumType.STRING)
	private LoanStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_person", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_book", nullable = false)
	private Book book;


}
