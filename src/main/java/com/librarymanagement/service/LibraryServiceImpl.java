package com.librarymanagement.service;

import com.librarymanagement.db.Library;
import com.librarymanagement.helpers.LibraryHelper;
import com.librarymanagement.obj.LibraryObj;
import com.librarymanagement.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryHelper helper;

    @Autowired
    private LibraryRepository repository;

    @Override
    public LibraryObj getLibrary() {
        List<Library> all = repository.findAll();
        return helper.libraryToObj(all.get(0));
    }

    @Override
    public LibraryObj createLibrary(LibraryObj obj) {
        List<Library> all = repository.findAll();
        if (all.size() == 0) {
            Library library = repository.saveAndFlush(helper.objToLibrary(obj));
            return helper.libraryToObj(library);
        }
        return updateLibrary(obj);
    }

    @Override
    public LibraryObj updateLibrary(LibraryObj obj) {
        List<Library> all = repository.findAll();
        if (all.size() == 0) {
            return createLibrary(obj);
        }
        obj.setId(all.get(0).getId());
        Library saved = repository.saveAndFlush(helper.objToLibrary(obj));
        return helper.libraryToObj(saved);
    }
}
