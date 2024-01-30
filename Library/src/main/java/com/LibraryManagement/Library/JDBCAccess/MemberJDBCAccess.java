package com.LibraryManagement.Library.JDBCAccess;

import com.LibraryManagement.Library.Dao.MemberDao;
import com.LibraryManagement.Library.Data.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("jdbc1")
public class MemberJDBCAccess implements MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberJDBCAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertMember(Member member) {
        var sql= """
                Insert into member(id,name,phone,registered_date)
                values (?,?,?,?)
                """;
        int result= jdbcTemplate.update(sql,member.getId(),member.getName(),member.getPhone(),member.getRegistered_date());
        System.out.println(result+"/rows affected");
    }

    @Override
    public void updateMember(Integer id, Member member) {
        var sql= """
                update member set id=?,name=?,phone=?,registered_date=? where id=?
                """;
        jdbcTemplate.update(sql,member.getId(),member.getName(),member.getPhone(),member.getRegistered_date(),id);
        System.out.println("Updated");
    }

    @Override
    public Optional<Member> getMember(Integer id) {
        var sql= """
                 select *
                 from member
                 where id=?;
                 """;
        RowMapper<Member> bookRowMapper=(rs, rowNumber)->{
            Member member=new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("registered_date")
            );
            return member;
        };
        Optional<Member> b=jdbcTemplate.query(sql,bookRowMapper,id).stream().findFirst();
        return b;
    }

    @Override
    public void deleteMember(Integer id) {
        var sql="""
                delete from member where id=?
                """;
        Object[] odj=new Object[]{id};
        jdbcTemplate.update(sql,odj);
        System.out.println("Member deleted successfully");
    }
}
