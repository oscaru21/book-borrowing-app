package com.appllication.teluslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appllication.teluslibrary.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
