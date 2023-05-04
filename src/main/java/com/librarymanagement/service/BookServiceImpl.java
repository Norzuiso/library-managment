package com.librarymanagement.service;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.helpers.BookHelper;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.PaginationBookObj;
import com.librarymanagement.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookHelper helper;

    @Override
    public BookObj createBook(BookObj obj) {
        Book book = helper.objToBook(obj);
        Book created = repository.saveAndFlush(book);
        return helper.bookToObj(created);
    }

    @Override
    public BookObj editBook(BookObj obj, Integer id) {
        obj.setId(id);
        return createBook(obj);
    }

    @Override
    public BookObj getBookById(Integer id) {
        Book referenceById = repository.getReferenceById(id);
        return helper.bookToObj(referenceById);
    }

    @Override
    public PaginationBookObj getBooksByPage(int page, int amountOfElements) {
        Page<Book> pages = repository.findAll(PageRequest.of(page, amountOfElements));
        return new PaginationBookObj(pages.getTotalElements(), helper.bookListToObjList(pages.getContent()));
    }

    @Override
    public List<BookObj> getAvailableBooks() {
        BookSearchRequest searchRequest = new BookSearchRequest();
        searchRequest.setIsAvailable(true);
        return getBookByFilter(searchRequest);
    }

    @Override
    public List<BookObj> getAllBooks() {
        List<Book> all = repository.findAll();
        return helper.bookListToObjList(all);
    }

    @Override
    public List<BookObj> getBookByFilter(BookSearchRequest searchRequest) {
        List<Book> filterBooks = repository.FindAllBooksByCriteria(searchRequest);
        if (filterBooks.isEmpty()){
            return getAllBooks();
        }
        return helper.bookListToObjList(filterBooks);
    }

    @Override
    public PaginationBookObj getAvailableBooksByPage(int page, int amountOfElements) {
        BookSearchRequest searchRequest = new BookSearchRequest();
        searchRequest.setIsAvailable(true);
        return getBooksByFilterAndPage(page, amountOfElements, searchRequest);
    }

    @Override
    public PaginationBookObj getBooksByFilterAndPage(int page, int amountOfElements, BookSearchRequest searchRequest) {
        Pageable pageable = PageRequest.of(page, amountOfElements);
        Page<Book> books = repository.FindAllBooksByCriteriaWithPages(searchRequest, pageable, amountOfElements);
        return new PaginationBookObj(books.getTotalElements(), helper.bookListToObjList(books.getContent()));
    }

    @Override
    public Boolean deleteBookById(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception constraintViolationException) {
            return false;
        }
    }
}
