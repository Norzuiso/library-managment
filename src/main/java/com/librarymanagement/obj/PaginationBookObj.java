package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationBookObj {
    private long total;
    private List<BookObj> content;
}
