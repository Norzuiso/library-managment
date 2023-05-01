package com.librarymanagement.service;

import com.librarymanagement.db.Loan;
import com.librarymanagement.helpers.LoanHelper;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.LoanObj;
import com.librarymanagement.obj.ReaderObj;
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

    @Autowired
    private ReaderService readerService;

    @Override
    public LoanObj createLoan(LoanObj obj) {
        Loan loan = helper.objToLoan(obj);
        Loan save = repository.saveAndFlush(loan);
        BookObj book = bookService.getBookById(obj.getBook().getId());
        long copiesQuantity = book.getCopiesQuantity();
        copiesQuantity -= 1;
        book.setCopiesQuantity(copiesQuantity);
        book.setIsAvailable(true);
        if (book.getCopiesQuantity() == 0) {
            book.setIsAvailable(false);
        }
        BookObj bookSaved = bookService.editBook(book, book.getId());

        ReaderObj reader = readerService.getById(obj.getReader().getId());

        obj.setBook(bookSaved);
        obj.setReader(reader);
        return helper.loanToObj(save);
    }

    @Override
    public LoanObj giveBackBook(LoanObj obj) {
        BookObj book = obj.getBook();
        long copiesQuantity = book.getCopiesQuantity();
        copiesQuantity += 1;
        book.setCopiesQuantity(copiesQuantity);
        BookObj bookSaved = bookService.editBook(book, book.getId());
        obj.setBook(bookSaved);
        Loan loan = helper.objToLoan(obj);
        loan.setIsActive(false);
        Loan save = repository.saveAndFlush(loan);
        return helper.loanToObj(save);
    }

    @Override
    public List<LoanObj> getAllLoans() {
        List<Loan> all = repository.findAllByOrderByIdDesc();
        return helper.loanListToObjList(all);
    }

    @Override
    public LoanObj getLoanById(Integer id) {
        Loan loan = repository.getReferenceById(id);
        return helper.loanToObj(loan);
    }

    @Override
    public Boolean deleteLoan(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
