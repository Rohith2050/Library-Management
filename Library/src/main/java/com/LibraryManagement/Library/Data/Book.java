package com.LibraryManagement.Library.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
   // @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String published_date;
    private int available_copies;

    public Book(int id, String title, String author, String isbn, String published_date, int available_copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.published_date = published_date;
        this.available_copies = available_copies;
    }

    public Book(int available_copies) {
        this.available_copies=available_copies;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return published_date;
    }

    public void setPublishedDate(String publishedDate) {
        this.published_date = publishedDate;
    }

    public int getAvailableCopies() {
        return available_copies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.available_copies = availableCopies;
    }
}
