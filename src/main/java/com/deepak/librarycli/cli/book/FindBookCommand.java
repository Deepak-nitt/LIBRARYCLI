package com.deepak.librarycli.cli.book;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.model.Genre;
import com.deepak.librarycli.service.BookService;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "find", description = "Search books by title (case-insensitive)")
public class FindBookCommand implements Runnable {

    @CommandLine.ParentCommand
    private BookCommands parent;

    @CommandLine.Option(names = "--title" , description = "Search by title")
    private String title;

    @CommandLine.Option(names = "--author" , description = "Search by author")
    private  String author;

    @CommandLine.Option(names = "--genre" , description = "Search by genre")
    private Genre genre;

    @CommandLine.Option(names = "--isbn", description = "Search by ISBN")
    private String isbn;



    @Override
    public void run() {
        try {
            List<Book> result;
            if (title != null) {
                result = parent.getBookService().searchByTitle(title);
            } else if (author != null) {
                result = parent.getBookService().searchByAuthor(author);
            } else if (isbn != null) {
                result = parent.getBookService().searchByIsbn(isbn)
                        .map(List::of)
                        .orElse(List.of());
            } else if (genre!=null) {
                result = parent.getBookService().searchByGenre(genre);

            } else {
                System.out.println("Provide --title, --author, or --isbn to search.");
                return;
            }

            if (result.isEmpty()) System.out.println("No books found.");
            else result.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error finding book: " + e.getMessage());
        }
    }
}


