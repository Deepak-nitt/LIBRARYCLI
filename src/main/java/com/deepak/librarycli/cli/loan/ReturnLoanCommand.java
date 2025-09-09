package com.deepak.librarycli.cli.loan;

import com.deepak.librarycli.model.Loan;
import com.deepak.librarycli.service.LoanService;
import picocli.CommandLine;

import java.time.LocalDate;

@CommandLine.Command(name = "return", description = "Return a book")
public class ReturnLoanCommand implements Runnable {

    @CommandLine.ParentCommand
    private LoanCommands parent;

    @CommandLine.Option(names = "--isbn", required = true, description = "ISBN of the book to return")
    private String isbn;

    @CommandLine.Option(names = "--member", required = true, description = "ID of the member returning the book")
    private String memberId;

    @CommandLine.Option(names = "--date", description = "Return date in format YYYY-MM-DD. Defaults to today.")
    private String returnDateStr;

    @Override
    public void run() {
        LoanService loanService = parent.getLoanService();
        try {
            LocalDate returnDate = (returnDateStr != null) ? LocalDate.parse(returnDateStr) : LocalDate.now();
            Loan loan = loanService.returnBook(memberId, isbn, returnDate);
            long overdueDays = loanService.calculateOverdueDays(loan);
            long fine = loanService.calculateFine(loan);

            System.out.println("Book returned successfully.");
            System.out.println("Overdue days: " + overdueDays);
            System.out.println("Fine: Rs. " + fine);
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}

