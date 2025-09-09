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
import java.util.stream.Collectors;

/**
 * Service for loan business logic.
 */
public class LoanService {

    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private final LoanRepository loanRepo;

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final int OVERDUE_FEE_PER_DAY = 10;

    public LoanService(BookRepository bookRepo, MemberRepository memberRepo, LoanRepository loanRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.loanRepo = loanRepo;
    }

    // Issue a book to a member if available and member is active
    public Loan issueBook(String memberId, String isbn, LocalDate issueDate) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("Member not found "+memberId));

        if(member.getStatus()!=MemberStatus.ACTIVE){
            throw new IllegalArgumentException("Member is not Active "+memberId);
        }
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(()->new IllegalArgumentException("Book not found"+isbn));
        if(!book.isAvailable()){
            throw new IllegalArgumentException("Book is not available"+isbn);
        }

        // Check if book is already issued and not returned
        boolean alreadyIssued = loanRepo.findActiveLoanByIsbn(isbn).isPresent();
        if (alreadyIssued) {
            throw new IllegalArgumentException("Book is already issued: " + isbn);
        }

        book.setAvailable(false);

        Loan loan = new Loan(member, book, issueDate, issueDate.plusDays(LOAN_PERIOD_DAYS));
        loanRepo.save(loan);
        return loan;
    }

    // Return a book
    public Loan returnBook(String memberId, String isbn, LocalDate returnDate) {
        Optional<Loan> activeLoanOpt = loanRepo.findAll().stream()
                .filter(l -> l.getBook().getIsbn().equals(isbn)
                        && l.getMember().getId().equals(memberId)
                        && l.getReturnDate() == null)
                .findFirst();

        if (activeLoanOpt.isEmpty()) {
            throw new IllegalArgumentException("No active loan found for member " + memberId + " and book " + isbn);
        }

        Loan loan = activeLoanOpt.get();
        loan.setReturnDate(returnDate);

        Book book = loan.getBook();
        book.setAvailable(true);

        loanRepo.update(loan); // Make sure loan is updated in repo
        return loan;
    }

    // Calculate overdue days (0 if returned on time or not returned yet)
    public long calculateOverdueDays(Loan loan) {
        LocalDate dueDate = loan.getDueDate();
        LocalDate effectiveReturn = loan.getReturnDate() != null ? loan.getReturnDate() : LocalDate.now();
        long overdueDays = ChronoUnit.DAYS.between(dueDate, effectiveReturn);
        return Math.max(overdueDays, 0);
    }

    // Calculate fine in Rs
    public long calculateFine(Loan loan) {
        return calculateOverdueDays(loan) * OVERDUE_FEE_PER_DAY;
    }

    // List all loans
    public List<Loan> listLoans() {
        return loanRepo.findAll();
    }

    // List loans by member
    public List<Loan> listLoansByMember(String memberId) {
        return loanRepo.findAll().stream()
                .filter(l -> l.getMember().getId().equals(memberId))
                .collect(Collectors.toList());
    }

    // List loans by book
    public List<Loan> listLoansByBook(String isbn) {
        return loanRepo.findAll().stream()
                .filter(l -> l.getBook().getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    // List overdue loans
    public List<Loan> listOverdueLoans() {
        return loanRepo.findAll().stream()
                .filter(l -> calculateOverdueDays(l) > 0)
                .collect(Collectors.toList());
    }
}

