package com.librarymanagement.service;

import com.librarymanagement.db.Reader;
import com.librarymanagement.db.ReaderSearchRequest;
import com.librarymanagement.helpers.ReaderHelper;
import com.librarymanagement.obj.PaginationReaderObj;
import com.librarymanagement.obj.ReaderObj;
import com.librarymanagement.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository repository;

    @Autowired
    private ReaderHelper helper;

    @Override
    public ReaderObj createReader(ReaderObj obj) {
        Reader reader = repository.saveAndFlush(helper.objToReader(obj));
        return helper.readerToObj(reader);
    }

    @Override
    public ReaderObj updateReader(ReaderObj obj, Integer id) {
        obj.setId(id);
        Reader reader = repository.saveAndFlush(helper.objToReader(obj));
        return helper.readerToObj(reader);
    }

    @Override
    public List<ReaderObj> getAllReader() {
        return helper.readerListToObjList(repository.findAll());
    }

    @Override
    public List<ReaderObj> getAllReaderFiltered(ReaderSearchRequest searchRequest) {
        List<Reader> readers = repository.FindAllReaderByCriteria(searchRequest);
        return helper.readerListToObjList(readers);
    }

    @Override
    public PaginationReaderObj getAllReaderByPage(int page, int amountOfElements) {
        Page<Reader> pages = repository.findAll(PageRequest.of(page, amountOfElements));
        return new PaginationReaderObj(pages.getTotalElements(), helper.readerListToObjList(pages.getContent()));
    }

    @Override
    public PaginationReaderObj getAllReaderByPageAndFilters(int page, int amountOfElements, ReaderSearchRequest searchRequest) {
        Pageable pageable = PageRequest.of(page, amountOfElements);
        Page<Reader> readers = repository.FindAllReaderByCriteriaWithPages(searchRequest, pageable, amountOfElements);
        return new PaginationReaderObj(readers.getTotalElements(), helper.readerListToObjList(readers.getContent()));
    }
}
