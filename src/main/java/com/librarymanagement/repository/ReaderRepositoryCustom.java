package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.db.Reader;
import com.librarymanagement.db.ReaderSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ReaderRepositoryCustom {
    List<Reader> FindAllReaderByCriteria(ReaderSearchRequest request);

    Page<Reader> FindAllReaderByCriteriaWithPages(ReaderSearchRequest request, Pageable page, long totalRows);
}
