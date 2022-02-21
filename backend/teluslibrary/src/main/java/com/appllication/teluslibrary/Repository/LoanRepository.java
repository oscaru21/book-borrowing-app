package com.appllication.teluslibrary.Repository;

import org.springframework.data.repository.CrudRepository;
import com.appllication.teluslibrary.Entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> 
{
	
}
