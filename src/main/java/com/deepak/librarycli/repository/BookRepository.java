package com.deepak.librarycli.repository;

import com.deepak.librarycli.model.Book;

import java.util.*;

public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();
    public void save(Book book){
        books.put(book.getIsbn(),book);
    }

    public Book findByIsbn(String isbn){
        return books.get(isbn);
    }

    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }

    public void delete(String isbn){
        books.remove(isbn);
    }

    public boolean existsByIsbn(String isbn){
        return books.containsKey(isbn);
    }




}
