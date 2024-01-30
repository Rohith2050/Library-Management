package com.LibraryManagement.Library.Dao;

import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.Data.Borrow;

import java.util.Optional;

public interface BorrowDao {

    void insertBorrow(Borrow borrow);

    void updateBorrow(Integer id, Borrow borrow);

    Optional<Borrow> getBorrow(Integer id);

    void deleteBorrow(Integer id);

    void borrowBook(Integer bookId, Integer memberId,Borrow borrow);

    void returnBook(Integer bookId, Integer memberId, Borrow borrow);
}
