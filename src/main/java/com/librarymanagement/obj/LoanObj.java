package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanObj {
    private Integer id;
    private LocalDate loanDate;
    private BookObj book;
    private ReaderObj reader;
}
