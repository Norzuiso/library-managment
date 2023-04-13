package com.librarymanagement.helpers;

import com.librarymanagement.db.Reader;
import com.librarymanagement.obj.ReaderObj;

import java.util.List;

public interface ReaderHelper {

    Reader objToReader(ReaderObj reader);

    ReaderObj readerToObj(Reader reader);

    List<ReaderObj> readerListToObjList(List<Reader> reader);

    List<Reader> objListToObjList(List<ReaderObj> reader);
}
