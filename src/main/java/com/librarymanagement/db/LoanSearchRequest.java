package com.librarymanagement.db;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanSearchRequest {
    private Integer id;
    private LocalDate loanDate;
    private Integer book;
    private Integer reader;
}
