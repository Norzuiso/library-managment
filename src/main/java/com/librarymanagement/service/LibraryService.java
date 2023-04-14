package com.librarymanagement.service;

import com.librarymanagement.db.Library;
import com.librarymanagement.obj.LibraryObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryService {
    LibraryObj getLibrary();
    LibraryObj createLibrary(LibraryObj obj);
    LibraryObj updateLibrary(LibraryObj obj);
}
