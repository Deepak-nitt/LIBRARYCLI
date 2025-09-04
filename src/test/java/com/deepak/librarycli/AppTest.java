package com.deepak.librarycli;

import com.deepak.librarycli.model.*;
import com.deepak.librarycli.repository.*;
import com.deepak.librarycli.service.*;

/**
 * Manual test harness for LibraryCLI.
 *
 * This simulates real usage:
 *  1. Add a book
 *  2. Register a member
 *  3. Issue the book to the member
 *  4. Return the book
 *  5. Print loan history
 *
 * Run this with: mvn exec:java -Dexec.mainClass="com.deepak.librarycli.TestApp"
 */
public class AppTest {
    public static void main(String[] args) {
        // Initialize repositories (in-memory storage)
        BookRepository bookRepo = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        LoanRepository loanRepo = new LoanRepository();

        // Initialize services
        BookService bookService = new BookService(bookRepo);
        MemberService memberService = new MemberService(memberRepo);
        LoanService loanService = new LoanService(bookRepo, memberRepo, loanRepo);

        // Step 1: Add a book
        Book book = new Book("9780001", "The Dark Knight", "Nolan", Genre.FANTASY, 2008);
        bookService.addBook(book);

        // Step 2: Register a member
        Member member = memberService.registerMember("John", "john@example.com");

        // Step 3: Issue a loan
        Loan loan = loanService.issueBook("9780001", member.getId());
        System.out.println("Loan issued: " + loan);

        // Step 4: Return the book
        loanService.returnBook("9780001");

        // Step 5: Print all loans
        System.out.println("All Loans: " + loanService.listLoans());
    }
}

