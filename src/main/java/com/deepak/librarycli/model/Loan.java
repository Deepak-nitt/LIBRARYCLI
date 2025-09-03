package com.deepak.librarycli.model;

import java.time.LocalDate;

public class Loan {

    private String isbn;
    private String memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Constructor to create a new loan
    public Loan(String isbn , String memberId , LocalDate issueDate , LocalDate dueDate){
        this.isbn =isbn;
        this.memberId =memberId;
        this.issueDate =issueDate;
        this.dueDate = dueDate;

    }

    // getter for the getIsbn;
    public String getIsbn(){
        return isbn;

    }

    // return the id of the member
    public String getMemberId(){
        return memberId;
    }

    // return the date when the book is issued
    public LocalDate getIssueDate(){
        return issueDate;
    }

    // return the due date of returning the book
    public LocalDate getDueDate(){
        return dueDate;
    }

    // return the date when book was actually returned
    public  LocalDate getReturnDate(){
        return returnDate;
    }

    // Setters for setting the return date when the book was actually returned
    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }



    // string representation of the loan details.
    @Override
    public String toString() {
        return String.format("Loan: Book %s -> Member %s (Issued: %s, Due: %s, Returned: %s)",
                isbn, memberId, issueDate, dueDate,
                returnDate != null ? returnDate : "Not Returned");
    }
}
