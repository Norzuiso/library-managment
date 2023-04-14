package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationLoanObj {
    private long total;
    private List<LoanObj> content;
}
