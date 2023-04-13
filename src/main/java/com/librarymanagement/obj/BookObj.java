package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookObj {
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