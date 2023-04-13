package com.librarymanagement.helpers;

import com.librarymanagement.db.Loan;
import com.librarymanagement.obj.LoanObj;

import java.util.List;

public interface LoanHelper {
    Loan objToLoan(LoanObj loan);

    LoanObj loanToObj(Loan loan);

    List<LoanObj> loanListToObjList(List<Loan> loan);

    List<Loan> objListToObjList(List<LoanObj> loan);
}
