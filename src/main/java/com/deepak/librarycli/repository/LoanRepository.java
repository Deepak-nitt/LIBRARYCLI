package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository {
    private final List<Loan> loans = new ArrayList<>();

    public void save(Loan loan){
        loans.add(loan);
    }

    public List<Loan> findAll(){
        return new ArrayList<>(loans);
    }

    public Optional<Loan> findActiveLoanByIsbn(String isbn){
        return  loans.stream()
                .filter(ln -> ln.getIsbn().equals(isbn) && ln.getReturnDate()==null)
                .findFirst();
    }
}
