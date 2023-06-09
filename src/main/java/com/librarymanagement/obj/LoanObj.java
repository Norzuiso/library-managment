package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanObj {
    private Integer id;
    private LocalDate loanDate;
    private LocalDate expiredDate;
    private Boolean isActive;
    private BookObj book;
    private ReaderObj reader;
}
