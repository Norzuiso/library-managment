package com.librarymanagement.service;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.PaginationBookObj;

import java.util.List;
import java.util.Map;

public interface BookService {
    BookObj createBook(BookObj obj);
    BookObj editBook(BookObj obj, Integer id);

    BookObj getBookById(Integer id);

    PaginationBookObj getBooksByPage(int page, int amountOfElements);
    List<BookObj> getAvailableBooks();
    List<BookObj> getAllBooks();

    List<BookObj> getBookByFilter(BookSearchRequest searchRequest);

    PaginationBookObj getAvailableBooksByPage(int page, int amountOfElements);

    PaginationBookObj getBooksByFilterAndPage(int page, int amountOfElements, BookSearchRequest searchRequest);

    Boolean deleteBookById(int id);
}