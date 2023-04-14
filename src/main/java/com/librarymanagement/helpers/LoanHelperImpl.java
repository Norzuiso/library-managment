package com.librarymanagement.helpers;

import com.librarymanagement.db.Loan;
import com.librarymanagement.obj.LoanObj;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanHelperImpl implements LoanHelper {

    @Autowired
    private BookHelper bookHelper;

    @Autowired
    private ReaderHelper readerHelper;

    @Override
    public Loan objToLoan(LoanObj obj) {
        Loan loan = new Loan();
        if (obj != null) {
            loan.setId(obj.getId() == null ? 0 : obj.getId());
            loan.setLoanDate(obj.getLoanDate() == null ? LocalDate.now() : obj.getLoanDate());
            loan.setExpiredDate(obj.getExpiredDate() == null ? LocalDate.now() : obj.getExpiredDate());
            loan.setBook(bookHelper.objToBook(obj.getBook()));
            loan.setReader(readerHelper.objToReader(obj.getReader()));
            loan.setIsActive(obj.getIsActive());
        }
        return loan;
    }

    @Override
    public LoanObj loanToObj(Loan loan) {
        LoanObj obj = new LoanObj();
        if (loan != null) {
            obj.setId(loan.getId() != null ? loan.getId() : 0);
            obj.setLoanDate(loan.getLoanDate() != null ? loan.getLoanDate() : LocalDate.now());
            obj.setIsActive(loan.getIsActive());
            obj.setBook(bookHelper.bookToObj(loan.getBook()));
            obj.setReader(readerHelper.readerToObj(loan.getReader()));
        }
        return obj;
    }

    @Override
    public List<LoanObj> loanListToObjList(List<Loan> loan) {
        return loan.stream().map(this::loanToObj).collect(Collectors.toList());
    }

    @Override
    public List<Loan> objListToObjList(List<LoanObj> loan) {
        return loan.stream().map(this::objToLoan).collect(Collectors.toList());
    }
}
