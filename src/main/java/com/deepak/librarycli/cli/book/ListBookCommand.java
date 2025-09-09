package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "list", description = "List all books")
public class ListBookCommand implements Runnable {

    @CommandLine.ParentCommand
    private BookCommands parent;

    @CommandLine.Option(names = "--sort", description = "Sort by 'title' or 'year'")
    private String sort;


    @Override
    public void run() {
        try {
            List<Book> books = parent.getBookService().listBooks(sort);
            if (books.isEmpty()) {
                System.out.println("No books available.");
            } else {
                books.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Error listing books: " + e.getMessage());
        }
    }
}
