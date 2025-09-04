package com.deepak.librarycli.service;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.model.Loan;
import com.deepak.librarycli.model.Member;
import com.deepak.librarycli.model.MemberStatus;
import com.deepak.librarycli.repository.BookRepository;
import com.deepak.librarycli.repository.LoanRepository;
import com.deepak.librarycli.repository.MemberRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Service for loan business logic.
 */
public class LoanService {
    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private final LoanRepository loanRepo;

    public LoanService(BookRepository bookRepo, MemberRepository memberRepo, LoanRepository loanRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.loanRepo = loanRepo;
    }

    public Loan issueBook(String isbn, String memberId) {
        Book book = bookRepo.findByIsbn(isbn);
        Member member = memberRepo.findById(memberId);

        if (book == null || !book.isAvailable()) {
            throw new IllegalArgumentException("Book not available.");
        }
        if (member == null || member.getStatus() == MemberStatus.INACTIVE) {
            throw new IllegalArgumentException("Member not valid/active.");
        }

        book.setAvailable(false);
        Loan loan = new Loan(isbn, memberId, LocalDate.now(), LocalDate.now().plusDays(14));
        loanRepo.save(loan);
        return loan;
    }

    public void returnBook(String isbn) {
        Optional<Loan> activeLoan = loanRepo.findActiveLoanByIsbn(isbn);
        if (activeLoan.isPresent()) {
            Loan loan = activeLoan.get();
            loan.setReturnDate(LocalDate.now());

            Book book = bookRepo.findByIsbn(isbn);
            if (book != null) {
                book.setAvailable(true);
            }
        } else {
            throw new IllegalArgumentException("No active loan found for ISBN: " + isbn);
        }
    }

    public long calculateOverdueDays(Loan loan) {
        if (loan.getReturnDate() == null) {
            return 0;
        }
        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
        return Math.max(daysLate, 0);
    }

    public long calculateFine(Loan loan) {
        return calculateOverdueDays(loan) * 10; // ₹10 per day
    }

    public List<Loan> listLoans() {
        return loanRepo.findAll();
    }
}
