package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.model.Genre;
import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

@CommandLine.Command(name = "update", description = "Update a book by ISBN")
public class UpdateBookCommand implements Runnable {

    @CommandLine.ParentCommand
    private BookCommands parent;

    @CommandLine.Option(names = "--isbn", required = true)
    private String isbn;

    @CommandLine.Option(names = "--title")
    private String title;

    @CommandLine.Option(names = "--author")
    private String author;

    @CommandLine.Option(names = "--genre")
    private Genre genre;

    @CommandLine.Option(names = "--year")
    private Integer year;

    @CommandLine.Option(names = "--available")
    private Boolean available;


    @Override
    public void run() {
        try {
            Book updated = parent.getBookService().updateBook(isbn, title, author, genre, year, available);
            System.out.println("Book updated successfully: " + updated);
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
}
