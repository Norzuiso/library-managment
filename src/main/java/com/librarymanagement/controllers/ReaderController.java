package com.librarymanagement.controllers;

import com.librarymanagement.db.ReaderSearchRequest;
import com.librarymanagement.obj.PaginationReaderObj;
import com.librarymanagement.obj.ReaderObj;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.ReaderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reader")
@Tag(name = "Reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;


    @PostMapping
    public ReaderObj createReader(@RequestBody ReaderObj obj) {
        return readerService.createReader(obj);
    }

    @PutMapping("/{id}")
    public ReaderObj updateReader(@RequestBody ReaderObj obj, @PathVariable Integer id) {
        return readerService.updateReader(obj, id);
    }

    @GetMapping
    public List<ReaderObj> getAllReader() {
        return readerService.getAllReader();
    }

    @PostMapping("/filter")
    public List<ReaderObj> getAllReaderFiltered(@RequestBody ReaderSearchRequest searchRequest) {
        return readerService.getAllReaderFiltered(searchRequest);
    }

    @GetMapping("/{page}/{amountOfElements}")
    public PaginationReaderObj getAllReaderByPage(@PathVariable int page,@PathVariable int amountOfElements) {
        return readerService.getAllReaderByPage(page, amountOfElements);
    }


    @PostMapping("/filter/{page}/{amountOfElements}")
    public PaginationReaderObj getAllReaderByPageAndFilters(@PathVariable int page, @PathVariable int amountOfElements, @RequestBody ReaderSearchRequest searchRequest) {
        return readerService.getAllReaderByPageAndFilters(page, amountOfElements, searchRequest);
    }
}
