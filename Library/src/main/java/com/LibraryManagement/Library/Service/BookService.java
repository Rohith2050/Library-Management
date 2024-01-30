package com.LibraryManagement.Library.Service;

import com.LibraryManagement.Library.Dao.BookDao;
import com.LibraryManagement.Library.Data.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookDao bookDao;
    private Book book;

    public BookService(@Qualifier("jdbc") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ResponseEntity<String> insertBook(Book book) {
        bookDao.insertBook(book);
        return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
    }


    public ResponseEntity<String> updateBook(Integer id, Book book) {
        bookDao.updateBook(id,book);
        return new ResponseEntity<>("Updated Book Title", HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Book>> getBook(Integer id) {
        return new ResponseEntity<>(bookDao.getBook(id), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteBook(Integer id) {
        bookDao.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Book>> getBookByTitle(String title) {
        return new ResponseEntity<>(bookDao.getBookByTitle(title), HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Book>> getBookByAuthor(String author) {
        return new ResponseEntity<>(bookDao.getBookByAuthor(author), HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Book>> getBookBYIsbn(String isbn) {
        return new ResponseEntity<>(bookDao.getBookByIsbn(isbn), HttpStatus.CREATED);
    }
}
