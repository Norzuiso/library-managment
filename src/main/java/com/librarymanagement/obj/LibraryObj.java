package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryObj {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String password;
}
