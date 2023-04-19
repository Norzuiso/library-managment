package com.librarymanagement.service;

import com.librarymanagement.obj.LibraryObj;

public interface LibraryService {
    LibraryObj getLibrary();
    LibraryObj createLibrary(LibraryObj obj);
    LibraryObj updateLibrary(LibraryObj obj);

    Boolean login(String password);
}
