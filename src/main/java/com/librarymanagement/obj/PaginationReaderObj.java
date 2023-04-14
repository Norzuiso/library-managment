package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationReaderObj {
    private long total;
    private List<ReaderObj> content;
}
