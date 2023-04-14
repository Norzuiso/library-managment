package com.librarymanagement.controllers;

import com.librarymanagement.obj.LoanObj;
import com.librarymanagement.service.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/loan")
@Tag(name = "Loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping
    private LoanObj createLoan(@RequestBody LoanObj obj) {
        return service.createLoan(obj);
    }

    @PostMapping("/back")
    private LoanObj giveBackBook(@RequestBody LoanObj obj) {
        return service.giveBackBook(obj);
    }

    @GetMapping
    private List<LoanObj> getAllLoans() {
        return service.getAllLoans();
    }
}
