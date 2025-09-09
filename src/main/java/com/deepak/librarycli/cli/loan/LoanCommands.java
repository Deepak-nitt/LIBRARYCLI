package com.deepak.librarycli.cli.loan;

import com.deepak.librarycli.service.LoanService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "loan",
        description = "Manage Loans",
        subcommands = {
                IssueLoanCommand.class,
                ReturnLoanCommand.class
        }
)
public class LoanCommands implements Runnable {

    private final LoanService loanService;

    public LoanCommands(LoanService loanService) {
        this.loanService = loanService;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    @Override
    public void run() {
        System.out.println("Use subcommands: issue, return");
    }
}

