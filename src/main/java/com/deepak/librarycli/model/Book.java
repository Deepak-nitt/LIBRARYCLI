package com.deepak.librarycli.model;

public class Book {
    // unique identifier for the book
    private String isbn;
    // Title of the book
    private String title;
    // Author of the book
    private String author;
    // Genre of the book
    private Genre genre;
    //Year the book was published
    private int year;
    //Availability status
    private boolean available;



    // Constructor  for the new book
    public Book(String isbn, String title , String author , Genre genre , int year ){
        this.isbn=isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.available = true;
    }

    // Getters
    // Getter for isbn
    public String getIsbn(){
        return isbn;

    }

    // Getter for title
    public String getTitle(){
        return title;

    }

    // Getter for the author
     public String getAuthor(){
        return author;
     }

     // Getter for the genre
    public  Genre getGenre(){
        return genre;
    }

     // Getter for the  year
    public  int getYear(){
        return year;
    }

    public  boolean isAvailable(){
        return available;
    }

    // setter
    public void setAvailable(boolean available){
        this.available=available;
    }

    @Override
    public String toString(){
        return String.format("[%s] %s by %s (%d) -%s",isbn,title,author,year,available ? "Available":"Not Available");
    }
}
