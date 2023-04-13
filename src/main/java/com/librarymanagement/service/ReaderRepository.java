package com.librarymanagement.service;

import com.librarymanagement.db.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository  extends JpaRepository<Reader, Integer> {
}
