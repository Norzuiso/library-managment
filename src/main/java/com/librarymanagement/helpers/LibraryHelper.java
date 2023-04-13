package com.librarymanagement.helpers;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.Library;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.LibraryObj;

import java.util.List;

public interface LibraryHelper {
    Library objToLibrary(LibraryObj library);

    LibraryObj libraryToObj(Library library);

    List<LibraryObj> libraryListToObjList(List<Library> library);

    List<Library> objListToObjList(List<LibraryObj> library);
}
