package com.deepak.librarycli.service;

import com.deepak.librarycli.model.Book;
import com.deepak.librarycli.repository.BookRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for book business logic.
 */
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book book) {
        if (bookRepo.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book already exists with ISBN: " + book.getIsbn());
        }
        bookRepo.save(book);
    }

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

    public List<Book> searchByTitle(String title) {
        return bookRepo.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}

