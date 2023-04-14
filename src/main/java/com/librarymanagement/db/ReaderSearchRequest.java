package com.librarymanagement.db;

import lombok.Data;

@Data
public class ReaderSearchRequest {
    private Integer id;
    private String name;
    private String address;
    private String phone;
}
