package com.librarymanagement.repository;

import com.librarymanagement.db.Reader;
import com.librarymanagement.obj.ReaderObj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository  extends JpaRepository<Reader, Integer>, ReaderRepositoryCustom {
}
