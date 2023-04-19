package com.librarymanagement.helpers;

import com.librarymanagement.db.Book;
import com.librarymanagement.obj.BookObj;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookHelperImpl implements BookHelper {
    @Override
    public Book objToBook(BookObj obj) {
        Book book = new Book();
        if (obj != null) {
            book.setTitle(obj.getTitle() == null ? "" : obj.getTitle());
            book.setAuthor(obj.getAuthor() == null ? "" : obj.getAuthor());
            book.setGender(obj.getGender() == null ? "" : obj.getGender());
            book.setPublishYear(obj.getPublishYear() == null ? "" : obj.getPublishYear());
            book.setEdition(obj.getEdition() == null ? "" : obj.getEdition());
            book.setCopiesQuantity(obj.getCopiesQuantity() == null ? 0 : obj.getCopiesQuantity());
            book.setId(obj.getId() == null ? 0 : obj.getId());
            book.setSynopsis(obj.getSynopsis() == null ? "" : obj.getSynopsis());
            book.setSCDD(obj.getSCDD() == null ? "" : obj.getSCDD());
            book.setIsAvailable(obj.getIsAvailable() == null || obj.getIsAvailable());
            book.setEditorial(obj.getEditorial() == null ? "" : obj.getEditorial());
        }
        return book;
    }

    @Override
    public BookObj bookToObj(Book obj) {
        BookObj book = new BookObj();
        if (obj != null) {
            book.setId(obj.getId() == null ? 0 : obj.getId());
            book.setTitle(obj.getTitle() == null ? "" : obj.getTitle());
            book.setAuthor(obj.getAuthor() == null ? "" : obj.getAuthor());
            book.setGender(obj.getGender() == null ? "" : obj.getGender());
            book.setEditorial(obj.getEditorial() == null ? "" : obj.getEditorial());
            book.setPublishYear(obj.getPublishYear() == null ? "" : obj.getPublishYear());
            book.setEdition(obj.getEdition() == null ? "" : obj.getEdition());
            book.setCopiesQuantity(obj.getCopiesQuantity() == null ? 0 : obj.getCopiesQuantity());
            book.setSynopsis(obj.getSynopsis() == null ? "" : obj.getSynopsis());
            book.setSCDD(obj.getSCDD() == null ? "" : obj.getSCDD());
            book.setIsAvailable(obj.getIsAvailable() == null || obj.getIsAvailable());
        }
        return book;
    }

    @Override
    public List<BookObj> bookListToObjList(List<Book> book) {
        return book.stream().map(this::bookToObj).collect(Collectors.toList());
    }

    @Override
    public List<Book> objListToObjList(List<BookObj> book) {
        return book.stream().map(this::objToBook).collect(Collectors.toList());
    }
}
