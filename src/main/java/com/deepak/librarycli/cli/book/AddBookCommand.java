package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.model.Genre;
import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Add a new book")
public class AddBookCommand implements Runnable {

    @CommandLine.ParentCommand
    private BookCommands parent;

    @CommandLine.Option(names = "--isbn", required = true)
    private String isbn;

    @CommandLine.Option(names = "--title", required = true)
    private  String title;

    @CommandLine.Option(names = "--author", required = true)
    private String author;

    @CommandLine.Option(names = "--genre", required = true)
    private Genre genre;

    @CommandLine.Option(names = "--year", required = true)
    private int year;


    @Override
    public void run() {
        try{
            Book book = new Book(isbn, title, author, genre, year);
            parent.getBookService().addBook(book);
            System.out.println("Book Added Successfully "+ book);

        } catch (Exception e){
            System.err.println("Error adding book: "+e.getMessage());
        }
    }
}
