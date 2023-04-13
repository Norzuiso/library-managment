package com.librarymanagement.repository;

import com.librarymanagement.db.Library;
import com.librarymanagement.obj.LibraryObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
}
