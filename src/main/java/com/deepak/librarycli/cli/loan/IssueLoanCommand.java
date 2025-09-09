package com.deepak.librarycli.cli.loan;

import com.deepak.librarycli.model.Loan;
import com.deepak.librarycli.service.LoanService;
import picocli.CommandLine;

import java.time.LocalDate;

@CommandLine.Command(name = "issue", description = "Issue a book to a member")
public class IssueLoanCommand implements Runnable {

    @CommandLine.ParentCommand
    private LoanCommands parent;

    @CommandLine.Option(names = "--isbn", required = true, description = "ISBN of the book to issue")
    private String isbn;

    @CommandLine.Option(names = "--member", required = true, description = "ID of the member")
    private String memberId;

    @CommandLine.Option(names = "--date", description = "Issue date in format YYYY-MM-DD. Defaults to today.")
    private String issueDateStr;

    @Override
    public void run() {
        LoanService loanService = parent.getLoanService();
        try {
            LocalDate issueDate = (issueDateStr != null) ? LocalDate.parse(issueDateStr) : LocalDate.now();
            Loan loan = loanService.issueBook(memberId, isbn, issueDate);
            System.out.println("Loan issued successfully: " + loan);
        } catch (Exception e) {
            System.out.println("Error issuing loan: " + e.getMessage());
        }
    }
}

