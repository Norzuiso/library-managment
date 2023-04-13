package com.librarymanagement.repository;

import com.librarymanagement.db.Loan;
import com.librarymanagement.obj.LoanObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository  extends JpaRepository<Loan, Integer> {
}
