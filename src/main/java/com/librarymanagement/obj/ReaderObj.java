package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderObj {
    private Integer id;
    private String name;
    private String address;
    private String phone;
}
