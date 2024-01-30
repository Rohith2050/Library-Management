package com.LibraryManagement.Library.Dao;

import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.UpdateRequest.BookUpdateRequest;

import java.util.Optional;

public interface BookDao {
    void insertBook(Book book);

    void updateBook(Integer id, Book book);

    Optional<Book> getBook(Integer id);

    void deleteBook(Integer id);

    Optional<Book> getBookByTitle(String title);

    Optional<Book> getBookByAuthor(String author);

    Optional<Book> getBookByIsbn(String isbn);
}
