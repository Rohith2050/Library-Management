package com.LibraryManagement.Library.Service;

import com.LibraryManagement.Library.Dao.BorrowDao;
import com.LibraryManagement.Library.Data.Borrow;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowDao borrowDao;

    public BorrowService(@Qualifier("jdbc2")BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    public ResponseEntity<String> insertBorrow(Borrow borrow) {
        borrowDao.insertBorrow(borrow);
        return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateBorrow(Integer id, Borrow borrow) {
        borrowDao.updateBorrow(id,borrow);
        return new ResponseEntity<>("Updated", HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Borrow>> getBorrow(Integer id) {
        return new ResponseEntity<>(borrowDao.getBorrow(id), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteBorrow(Integer id) {
        borrowDao.deleteBorrow(id);
        return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
    }

    public ResponseEntity<String> borrowBook(Integer bookId, Integer memberId, Borrow borrow) {
        borrowDao.borrowBook(bookId,memberId,borrow);
        return new ResponseEntity<>("Borrowed_Book_Updated", HttpStatus.CREATED);
    }

    public ResponseEntity<String> returnBook(Integer bookId, Integer memberId, Borrow borrow) {
        borrowDao.returnBook(bookId,memberId,borrow);
        return new ResponseEntity<>("Returned_Book_Updated", HttpStatus.CREATED);
    }
}
