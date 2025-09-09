package com.deepak.librarycli.service;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.model.Genre;
import com.deepak.librarycli.repository.BookRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for book business logic.
 */
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Get a book by isbn
    public Book getBookByIsbn(String isbn){
        return bookRepo.findByIsbn(isbn)
                .orElseThrow(()-> new IllegalArgumentException("Book not found with ISBN "+isbn));
    }

    // Add a new book

    public void addBook(Book book) {
        if(book ==null) throw new IllegalArgumentException("Book cannot be null");
        if (bookRepo.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book already exists with ISBN: " + book.getIsbn());
        }
        bookRepo.save(book);
    }


    // updating an existing book
    public Book updateBook(String isbn, String title, String author, Genre genre, Integer year, Boolean available) {
        Book existing = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ISBN: " + isbn));

        if (title != null) existing.setTitle(title);
        if (author != null) existing.setAuthor(author);
        if (genre != null) existing.setGenre(genre);
        if (year != null) existing.setYear(year);
        if (available != null) existing.setAvailable(available);

        bookRepo.update(isbn, existing);
        return existing;
    }



    // delete a book
    public boolean deleteBook(String isbn){
        return bookRepo.delete(isbn);
    }

    // sorting of book  with title and year
    public List<Book> listBooks(String sortBy) {
        List<Book> books = bookRepo.findAll();
        if ("title".equalsIgnoreCase(sortBy)) {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .collect(Collectors.toList());
        } else if ("year".equalsIgnoreCase(sortBy)) {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getYear))
                    .collect(Collectors.toList());
        }
        return books;
    }

    // Search book by title (case-insensitive)
    public List<Book> searchByTitle(String title) {
        return bookRepo.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // search book by author
    public List<Book> searchByAuthor(String author){
        return bookRepo.findAll().stream()
                .filter(b->b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
    // search book by genre
    public List<Book> searchByGenre(Genre genre){
        return bookRepo.findAll().stream()
                .filter(b->b.getGenre()==genre)
                .collect(Collectors.toList());
    }

    // search book by ISBN
    public Optional<Book> searchByIsbn(String isbn){
        return bookRepo.findByIsbn(isbn);
    }
}

