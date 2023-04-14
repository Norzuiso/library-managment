package com.librarymanagement.controllers;

import com.librarymanagement.db.BookSearchRequest;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.PaginationBookObj;
import com.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
@Tag(name = "Book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookObj createBook(@RequestBody BookObj obj) {
        return bookService.createBook(obj);
    }

    @PutMapping("/{id}")
    public BookObj editBook(@RequestBody BookObj obj, @PathVariable Integer id) {
        return bookService.editBook(obj, id);
    }


    @GetMapping
    public List<BookObj> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{page}/{amountOfElements}")
    public PaginationBookObj getBooksByPage(@PathVariable int page, @PathVariable int amountOfElements) {
        return bookService.getBooksByPage(page, amountOfElements);
    }

    @GetMapping("/available")
    public List<BookObj> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/available/{page}/{amountOfElements}")
    public PaginationBookObj getAvailableBooksByPage(@PathVariable int page, @PathVariable int amountOfElements) {
        return bookService.getAvailableBooksByPage(page, amountOfElements);
    }


    @PostMapping("/filter")
    public List<BookObj> getBookByFilter(@RequestBody BookSearchRequest searchRequest) {
        return bookService.getBookByFilter(searchRequest);
    }

    @PostMapping("/filter/{page}/{amountOfElements}")
    public PaginationBookObj getBooksByFilterAndPage(@PathVariable int page, @PathVariable int amountOfElements, @RequestBody BookSearchRequest searchRequest) {
        return bookService.getBooksByFilterAndPage(page, amountOfElements, searchRequest);
    }
}
