package com.librarymanagement.helpers;

import com.librarymanagement.db.Library;
import com.librarymanagement.obj.LibraryObj;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryHelperImpl implements LibraryHelper {
    @Override
    public Library objToLibrary(LibraryObj obj) {
        Library library = new Library();
        if (obj != null) {
            library.setId(obj.getId() == null ? 0 : obj.getId());
            library.setName(obj.getName() == null ? "" : obj.getName());
            library.setAddress(obj.getAddress() == null ? "" : obj.getAddress());
            library.setPassword(obj.getPassword() == null ? "" : obj.getPassword());
            library.setPhone(obj.getPhone() == null ? "" : obj.getPhone());
        }
        return library;
    }

    @Override
    public LibraryObj libraryToObj(Library library) {
        LibraryObj obj = new LibraryObj();
        if (library != null) {
            obj.setId(library.getId() != null ? library.getId() : 0);
            obj.setName(library.getName() != null ? library.getName() : "");
            obj.setAddress(library.getAddress() != null ? library.getAddress() : "");
            obj.setPassword(library.getPassword() != null ? library.getPassword() : "");
            obj.setPhone(library.getPhone() != null ? library.getPhone() : "");
        }
        return obj;
    }

    @Override
    public List<LibraryObj> libraryListToObjList(List<Library> library) {
        return library.stream().map(this::libraryToObj).collect(Collectors.toList());
    }

    @Override
    public List<Library> objListToObjList(List<LibraryObj> library) {
        return library.stream().map(this::objToLibrary).collect(Collectors.toList());
    }
}
