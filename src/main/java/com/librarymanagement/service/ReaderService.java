package com.librarymanagement.service;

import com.librarymanagement.db.Reader;
import com.librarymanagement.db.ReaderSearchRequest;
import com.librarymanagement.obj.PaginationReaderObj;
import com.librarymanagement.obj.ReaderObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderService {
    ReaderObj createReader(ReaderObj obj);
    ReaderObj updateReader(ReaderObj obj, Integer id);
    List<ReaderObj> getAllReader();
    List<ReaderObj> getAllReaderFiltered(ReaderSearchRequest searchRequest);

    PaginationReaderObj getAllReaderByPage(int page, int amountOfElements);
    PaginationReaderObj getAllReaderByPageAndFilters(int page, int amountOfElements, ReaderSearchRequest searchRequest);



}
