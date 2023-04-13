package com.librarymanagement.helpers;

import com.librarymanagement.db.Reader;
import com.librarymanagement.obj.ReaderObj;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderHelperImpl implements ReaderHelper {
    @Override
    public Reader objToReader(ReaderObj obj) {
        Reader reader = new Reader();
        if (obj != null) {
            reader.setId(obj.getId() == null ? 0 : obj.getId());
            reader.setName(obj.getName() == null ? "" : obj.getName());
            reader.setAddress(obj.getAddress() == null ? "" : obj.getAddress());
            reader.setPhone(obj.getPhone() == null ? "" : obj.getPhone());
        }
        return reader;
    }

    @Override
    public ReaderObj readerToObj(Reader reader) {
        ReaderObj obj = new ReaderObj();
        if (reader != null) {
            obj.setId(reader.getId() != null ? reader.getId() : 0);
            obj.setName(reader.getName() != null ? reader.getName() : "");
            obj.setAddress(reader.getAddress() != null ? reader.getAddress() : "");
            obj.setPhone(reader.getPhone() != null ? reader.getPhone() : "");
        }
        return null;
    }

    @Override
    public List<ReaderObj> readerListToObjList(List<Reader> reader) {
        return reader.stream().map(this::readerToObj).collect(Collectors.toList());
    }

    @Override
    public List<Reader> objListToObjList(List<ReaderObj> reader) {
        return reader.stream().map(this::objToReader).collect(Collectors.toList());
    }
}
