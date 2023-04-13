package com.librarymanagement.helpers;

import com.librarymanagement.db.Book;
import com.librarymanagement.obj.BookObj;

import java.util.List;

public interface BookHelper {
    Book objToBook(BookObj book);

    BookObj bookToObj(Book book);

    List<BookObj> bookListToObjList(List<Book> book);

    List<Book> objListToObjList(List<BookObj> book);
}