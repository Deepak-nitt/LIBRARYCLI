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
        this.available = true; // available by default
    }

    // Getters and setters
    // Getter for isbn
    public String getIsbn(){
        return isbn;

    }
    public void setIsbn(String isbn){
        this.isbn =isbn;

    }

    // Getter for title
    public String getTitle(){
        return title;

    }

    public void setTitle(String title){
        this.title = title;
    }

    // Getter for the author
     public String getAuthor(){
        return author;
     }

     public void setAuthor( String author){
        this.author=author;
     }

     // Getter for the genre
    public  Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }
     // Getter for the  year
    public  int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }

    public  boolean isAvailable(){
        return available;
    }

    // setter
    public void setAvailable(boolean available){
        this.available=available;
    }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title=%s, Author=%s, Genre=%s, Year=%d, Available=%b]",
                isbn, title, author, genre, year, available);
    }
}
