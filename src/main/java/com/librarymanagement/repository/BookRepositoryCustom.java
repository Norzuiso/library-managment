package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface BookRepositoryCustom {
    List<Book> FindAllBooksByCriteria(BookSearchRequest request);

    Page<Book> FindAllBooksByCriteriaWithPages(BookSearchRequest request, Pageable page, long totalRows);
}
