package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.db.Loan;
import com.librarymanagement.db.LoanSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface LoanRepositoryCustom {
    List<Loan> FindAllLoansByCriteria(LoanSearchRequest request);

    Page<Loan> FindAllLoanByCriteriaWithPages(LoanSearchRequest request, Pageable page, long totalRows);
}
