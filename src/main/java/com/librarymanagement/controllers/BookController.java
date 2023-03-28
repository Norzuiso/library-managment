package com.librarymanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/book")
@Tag(name = "Book")
public class BookController {

    @GetMapping
    public String getBook(){
        return "This is a book";
    }
}
