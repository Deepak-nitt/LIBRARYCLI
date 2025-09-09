package com.deepak.librarycli;

import com.deepak.librarycli.cli.book.*;
import com.deepak.librarycli.cli.member.*;
import com.deepak.librarycli.cli.loan.*;
import com.deepak.librarycli.repository.*;
import com.deepak.librarycli.service.*;
import picocli.CommandLine;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initialize Repositories
        BookRepository bookRepo = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        LoanRepository loanRepo = new LoanRepository();

        // Initialize Services
        BookService bookService = new BookService(bookRepo);
        MemberService memberService = new MemberService(memberRepo);
        LoanService loanService = new LoanService(bookRepo, memberRepo, loanRepo);

        // Initialize command
        BookCommands bookCmd = new BookCommands(bookService);
        MemberCommands memberCmd = new MemberCommands(memberService);
        LoanCommands loanCmd = new LoanCommands(loanService);

        // Setup CommandLine with Library CLI
        CommandLine cli = new CommandLine(new LibraryCli());
        cli.addSubcommand("book",bookCmd);
        cli.addSubcommand("member",memberCmd);
        cli.addSubcommand("loan",loanCmd);



        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library CLI! Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("exit")) {
                System.out.println("Exiting Library CLI. Goodbye!");
                break;
            }

            // Parse input respecting quotes
            String[] arguments = parseArguments(input);

            try {
                cli.execute(arguments);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // Simple parser that handles quoted arguments
    private static String[] parseArguments(String input) {
        return input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
