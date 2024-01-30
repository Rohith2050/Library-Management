package com.LibraryManagement.Library.Controller;


import com.LibraryManagement.Library.Data.Borrow;
import com.LibraryManagement.Library.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    //CRUD - Operations for Borrow

    @PostMapping("add")
    public ResponseEntity<String> createBorrow(@RequestBody Borrow borrow){
       return borrowService.insertBorrow(borrow);
    }

    @PutMapping("{id}")
    public ResponseEntity<String>  updateBorrow(@PathVariable("id")Integer id,@RequestBody Borrow borrow){
       return borrowService.updateBorrow(id,borrow);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<Optional<Borrow>> getBorrow(@PathVariable("id")Integer id){
        return borrowService.getBorrow(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>  deleteBorrow(@PathVariable("id")Integer id){
       return borrowService.deleteBorrow(id);
    }


    //Borrow-management

    @PostMapping("BookBorrow")
    public ResponseEntity<String>  borrowBook(@RequestParam Integer book_id,@RequestParam Integer borrowAmt,@RequestBody Borrow borrow){
       return borrowService.borrowBook(book_id,borrowAmt,borrow);
    }
    @PostMapping("BookReturn")
    public ResponseEntity<String>  returnBook(@RequestParam Integer book_id,@RequestParam Integer member_id,@RequestBody Borrow borrow){
        return borrowService.returnBook(book_id,member_id,borrow);
    }
}
