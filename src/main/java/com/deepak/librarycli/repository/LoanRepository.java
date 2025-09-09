package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Loan;

import java.util.*;

public class LoanRepository {
    private final Map<String, Loan> loans = new HashMap<>();
    private int loanCounter = 1; // For generating loan IDs like L0001

    // Save a new loan
    public void save(Loan loan) {
        if(loan==null){
            throw new IllegalArgumentException("Loan cannot be null");
        }
        if (loan.getId() == null) {
            String id = String.format("L%04d", loanCounter++);
            loan.setId(id);
        }
        loans.put(loan.getId(), loan);
    }

    // Update an existing loan
    public boolean update(Loan loan) {
        if(loan==null) return  false;
        if (loan.getId() == null || !loans.containsKey(loan.getId())) return false;
        loans.put(loan.getId(), loan);
        return true;
    }

    // Find loan by ID
    public Optional<Loan> findById(String id) {
        return Optional.ofNullable(loans.get(id));
    }

    // List all loans
    public List<Loan> findAll() {
        return new ArrayList<>(loans.values());
    }

    // Delete a loan by ID
    public boolean delete(String id) {
        return loans.remove(id) != null;
    }

    // Find active loan by ISBN (no return date)
    public Optional<Loan> findActiveLoanByIsbn(String isbn) {
        return loans.values().stream()
                .filter(l -> l.getBook().getIsbn().equals(isbn) && l.getReturnDate() == null)
                .findFirst();
    }

    // Find active loans by member ID
    public List<Loan> findActiveLoansByMember(String memberId) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loans.values()) {
            if (loan.getMember().getId().equals(memberId) && loan.getReturnDate() == null) {
                result.add(loan);
            }
        }
        return result;
    }
}

