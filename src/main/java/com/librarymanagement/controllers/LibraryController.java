package com.librarymanagement.controllers;

import com.librarymanagement.obj.LibraryObj;
import com.librarymanagement.service.LibraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/library")
@Tag(name = "Library")
public class LibraryController {

    @Autowired
    private LibraryService service;

    @GetMapping
    LibraryObj getLibrary() {
        return service.getLibrary();
    }

    @PostMapping
    LibraryObj createLibrary(@RequestBody LibraryObj obj) {
        return service.createLibrary(obj);
    }

    @PutMapping
    LibraryObj updateLibrary(@RequestBody LibraryObj obj) {
        return service.updateLibrary(obj);
    }
}
