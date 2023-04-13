package com.librarymanagement.service;

import com.librarymanagement.db.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository  extends JpaRepository<Loan, Integer> {
}
