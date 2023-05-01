package com.librarymanagement.service;

import com.librarymanagement.db.Loan;
import com.librarymanagement.obj.LoanObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanService{
    LoanObj createLoan(LoanObj obj);
    LoanObj giveBackBook(LoanObj obj);

    List<LoanObj> getAllLoans();

    LoanObj getLoanById(Integer id);

    Boolean deleteLoan(Integer id);
}
