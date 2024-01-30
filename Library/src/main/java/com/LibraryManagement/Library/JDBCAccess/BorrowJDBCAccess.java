package com.LibraryManagement.Library.JDBCAccess;

import com.LibraryManagement.Library.Dao.BorrowDao;
import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.Data.Borrow;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository("jdbc2")
public class BorrowJDBCAccess implements BorrowDao {

    private final JdbcTemplate jdbcTemplate;

    public BorrowJDBCAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void insertBorrow(Borrow borrow) {
        var sql= """
                Insert into borrow(id,member_id,book_id,borrowed_date,due_date)
                values (?,?,?,?,?)
                """;
        int result= jdbcTemplate.update(sql,borrow.getId(),borrow.getMember_id(),borrow.getBook_id(),borrow.getBorrowed_date(),borrow.getDue_date());
        System.out.println(result+"/rows affected");
    }

    @Override
    public void updateBorrow(Integer id, Borrow borrow) {
        var sql= """
                update borrow set id=?,member_id=?,book_id=?,borrowed_date=?,due_date=? where id=?
                """;
        jdbcTemplate.update(sql,borrow.getId(),borrow.getMember_id(),borrow.getBook_id(),borrow.getBorrowed_date(),borrow.getDue_date(),id);
        System.out.println("updated");
    }

    @Override
    public Optional<Borrow> getBorrow(Integer id) {
        var sql= """
                 select *
                 from borrow
                 where id=?;
                 """;
        RowMapper<Borrow> borrowRowMapper=(rs, rowNumber)->{
            Borrow borrow=new Borrow(
                    rs.getInt("id"),
                    rs.getInt("book_id"),
                    rs.getInt("member_id"),
                    rs.getString("borrowed_date"),
                    rs.getString("due_date")
            );
            return borrow;
        };
        Optional<Borrow> b=jdbcTemplate.query(sql,borrowRowMapper,id).stream().findFirst();
        return b;
    }

    @Override
    public void deleteBorrow(Integer id) {
        var sql="""
                delete from borrow where id=?
                """;
        Object[] odj=new Object[]{id};
        jdbcTemplate.update(sql,odj);
        System.out.println("Deleted");
    }

    @Override
    public void borrowBook(Integer bookId, Integer borrowAmt,Borrow borrow) {
        var sql= """
                 select *
                 from book
                 where id=?;
                 """;
        RowMapper<Book> borrowRowMapper=(rs, rowNumber)->{
            Book book=new Book(
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,borrowRowMapper,bookId).stream().findFirst();

        int copies=b.get().getAvailableCopies();

        if(copies>0 && copies>borrowAmt){
            copies=copies-borrowAmt;
            var sql1= """
                update book set available_copies=? where id=?
                """;
            jdbcTemplate.update(sql1,copies,bookId);
            System.out.println("updated "+copies);
            try {
                var sql2= """
                Insert into borrow(id,book_id,borrowed_date,due_date,member_id)
                values(?,?,?,?,?)
                """;
                int result= jdbcTemplate.update(sql2,borrow.getId(),borrow.getBook_id(),borrow.getBorrowed_date(),borrow.getDue_date(),borrow.getMember_id());
                System.out.println(result+"/rows affected");
            }catch (DataAccessException e){
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Not available"+copies);
        }
    }

    @Override
    public void returnBook(Integer bookId, Integer memberId, Borrow borrow) {
        var sql= """
                 select *
                 from book
                 where id=?;
                 """;
        RowMapper<Book> borrowRowMapper=(rs, rowNumber)->{
            Book book=new Book(
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,borrowRowMapper,bookId).stream().findFirst();

        int copies=b.get().getAvailableCopies();

        if(copies>=0){
            copies++;
            var sql1= """
                update book set available_copies=? where id=?
                """;
            jdbcTemplate.update(sql1,copies,bookId);
            System.out.println("updated "+copies);

            var sql2= """
               update borrow set due_date=? where id=?
                """;
            int result= jdbcTemplate.update(sql2,borrow.getDue_date(),memberId);
            System.out.println(result+"/rows affected");

        }else{
            System.out.println("Not available"+copies);
        }
    }
}
