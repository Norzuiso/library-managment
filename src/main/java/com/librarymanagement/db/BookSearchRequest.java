package com.librarymanagement.db;

import lombok.Data;

@Data
public class BookSearchRequest {
    private Integer id;
    private String title;
    private String author;
    private String gender;
    private String editorial;
    private String publishYear;
    private String edition;
    private Long copiesQuantity;
    private String synopsis;
    private String SCDD;
    private Boolean isAvailable;
}
