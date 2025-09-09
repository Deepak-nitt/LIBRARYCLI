package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

@CommandLine.Command(name = "delete", description = "Delete a book by ISBN")
public class DeleteBookCommand implements Runnable {

    @CommandLine.ParentCommand
    private BookCommands parent;

    @CommandLine.Option(names = "--isbn", required = true)
    private String isbn;

    @Override
    public void run() {
        try {
            boolean deleted = parent.getBookService().deleteBook(isbn);
            if (deleted) System.out.println("Book deleted: " + isbn);
            else System.out.println("Book not found: " + isbn);
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}