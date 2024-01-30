package com.LibraryManagement.Library.Controller;

import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Book")
public class BookController {

    @Autowired
    private BookService bookService;

    //CRUD - Operations for Book

    // Create
    @PostMapping("add")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }

    //Update
    @PutMapping("{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id")Integer id,@RequestBody Book book){
       return bookService.updateBook(id,book);
    }

    //Read
    @GetMapping("get/{id}")
    public ResponseEntity<Optional<Book>> getBook1(@PathVariable("id")Integer id){
        return bookService.getBook(id);
    }

    @GetMapping("Title/{ByTitle}")
    public ResponseEntity<Optional<Book>> getBookByTitle(@PathVariable("ByTitle") String title){
        return bookService.getBookByTitle(title);
    }

    @GetMapping("Author/{ByAuthor}")
    public ResponseEntity<Optional<Book>> getBookByAuthor(@PathVariable("ByAuthor") String author){
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("Isbn/{Isbn}")
    public ResponseEntity<Optional<Book>> getBookByIsbn(@PathVariable("Isbn") String isbn){
        return bookService.getBookBYIsbn(isbn);
    }

    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id")Integer id){
        return bookService.deleteBook(id);
    }
}
