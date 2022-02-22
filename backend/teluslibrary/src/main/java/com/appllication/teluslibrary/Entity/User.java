package com.appllication.teluslibrary.Entity;
import java.util.Objects;
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
@Table(name="USER",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})

public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@JsonProperty("name")
	@Column(name = "name")
	private String name;

	@NonNull
	@JsonProperty("nbooks")
	@Column(name = "nbooks")
	private String nbooks;

	@OneToMany(mappedBy = "fk_user")
    Set<Loan> Loans;
}