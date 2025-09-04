package com.deepak.librarycli;

import com.deepak.librarycli.model.*;
import com.deepak.librarycli.repository.*;
import com.deepak.librarycli.service.*;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepo = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        LoanRepository loanRepo = new LoanRepository();

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

        // Step 5: Print loans
        System.out.println("Loans: " + loanService.listLoans());
    }
}

