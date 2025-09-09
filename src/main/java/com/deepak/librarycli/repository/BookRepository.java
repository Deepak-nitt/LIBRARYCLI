package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Book;

import java.util.*;

public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    // save or update the book
    public void save(Book book){
        if(book == null || book.getIsbn()==null){
            throw new IllegalArgumentException("Book or ISBN cannot be null");
        }
        books.put(book.getIsbn(),book);
    }

    // update the existing book'
    public boolean update(String isbn , Book updateBook){
        if(!books.containsKey(isbn)) return false;
        books.put(isbn,updateBook);
        return true;
    }
    // Find a Book by ISBN
    public Optional<Book> findByIsbn(String isbn)
    {
        return Optional.ofNullable(books.get(isbn));
    }


    // List all The books
    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }

    // Delete a Book
    public boolean delete(String isbn){
       return books.remove(isbn)!= null;
    }

    // check if book exists
    public boolean existsByIsbn(String isbn){
        return books.containsKey(isbn);
    }




}
