package com.librarymanagement.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Builder
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String gender;
    private String editorial;
    private String publishYear;
    private String edition;
    private Long copiesQuantity;

    @Column( length = 1000 )
    private String synopsis;
    private String SCDD;
    private Boolean isAvailable;
}