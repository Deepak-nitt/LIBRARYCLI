package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "book",
        description = "Manage Books",
        subcommands = {
                AddBookCommand.class,
                ListBookCommand.class,
                FindBookCommand.class,
                UpdateBookCommand.class,
                DeleteBookCommand.class
        }
)
public class BookCommands implements Runnable {

    private final BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run() {
        System.out.println("Use subcommands: add, list, find, update, delete");
    }

    public BookService getBookService() {
        return bookService;
    }
}


