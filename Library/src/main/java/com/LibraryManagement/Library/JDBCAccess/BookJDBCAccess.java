package com.LibraryManagement.Library.JDBCAccess;

import com.LibraryManagement.Library.Dao.BookDao;
import com.LibraryManagement.Library.Data.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository("jdbc")
public class BookJDBCAccess implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookJDBCAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertBook(Book book) {
        var sql= """
                Insert into book(id,title,author,isbn,published_date,available_copies)
                values (?,?,?,?,?,?)
                """;
        int result= jdbcTemplate.update(sql,book.getId(),book.getTitle(),book.getAuthor(),book.getIsbn(),book.getPublishedDate(),book.getAvailableCopies());
        System.out.println(result+"/rows affected");
    }

    @Override
    public void updateBook(Integer id, Book book) {
        var sql= """
                update book set id=?,title=?,author=?,isbn=?,published_date=?,available_copies=? where id=?
                """;
        jdbcTemplate.update(sql,book.getId(),book.getTitle(),book.getAuthor(),book.getIsbn(),book.getPublishedDate(),book.getAvailableCopies(),id);
        System.out.println("updated");
    }

    @Override
    public Optional<Book> getBook(Integer id) {
        var sql= """
                 select *
                 from book 
                 where id=?;
                 """;
        RowMapper<Book> bookRowMapper=(rs,rowNumber)->{
            Book book=new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getString("published_date"),
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,bookRowMapper,id).stream().findFirst();
        return b;
    }

    @Override
    public void deleteBook(Integer id) {
        var sql="""
                delete from book where id=?
                """;
        Object[] odj=new Object[]{id};
        jdbcTemplate.update(sql,odj);
        System.out.println("Deleted");
    }

    @Override
    public Optional<Book> getBookByTitle(String title) {
        var sql= """
                 select *
                 from book
                 where title=?;
                 """;
        RowMapper<Book> bookRowMapper=(rs,rowNumber)->{
            Book book=new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getString("published_date"),
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,bookRowMapper,title).stream().findFirst();
        return b;
    }

    @Override
    public Optional<Book> getBookByAuthor(String author) {
        var sql= """
                 select *
                 from book
                 where author=?;
                 """;
        RowMapper<Book> bookRowMapper=(rs,rowNumber)->{
            Book book=new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getString("published_date"),
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,bookRowMapper,author).stream().findFirst();
        return b;
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        var sql= """
                 select *
                 from book
                 where isbn=?;
                 """;
        RowMapper<Book> bookRowMapper=(rs,rowNumber)->{
            Book book=new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getString("published_date"),
                    rs.getInt("available_copies")
            );
            return book;
        };
        Optional<Book> b=jdbcTemplate.query(sql,bookRowMapper,isbn).stream().findFirst();
        return b;
    }
}
