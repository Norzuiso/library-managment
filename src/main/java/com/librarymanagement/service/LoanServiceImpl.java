package com.librarymanagement.service;

import com.librarymanagement.db.Loan;
import com.librarymanagement.helpers.LoanHelper;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.LoanObj;
import com.librarymanagement.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanHelper helper;

    @Autowired
    private LoanRepository repository;

    @Autowired
    private BookService bookService;


    @Override
    public LoanObj createLoan(LoanObj obj) {
        BookObj book = obj.getBook();
        book.setCopiesQuantity(book.getCopiesQuantity()-1);
        BookObj bookSaved = bookService.editBook(book, book.getId());
        obj.setBook(bookSaved);

        Loan loan = helper.objToLoan(obj);
        Loan save = repository.saveAndFlush(loan);
        return helper.loanToObj(save);
    }

    @Override
    public LoanObj giveBackBook(LoanObj obj) {
        BookObj book = obj.getBook();
        book.setCopiesQuantity(book.getCopiesQuantity()+1);
        BookObj bookSaved = bookService.editBook(book, book.getId());
        obj.setBook(bookSaved);

        Loan loan = helper.objToLoan(obj);
        Loan save = repository.saveAndFlush(loan);
        return helper.loanToObj(save);
    }

    @Override
    public List<LoanObj> getAllLoans() {
        List<Loan> all = repository.findAll();
        return helper.loanListToObjList(all);
    }
}
