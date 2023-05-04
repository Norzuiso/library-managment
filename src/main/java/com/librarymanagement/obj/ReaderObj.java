package com.librarymanagement.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReaderObj {
    private Integer id;
    private String name;
    private String address;
    private String phone;
}
