package com.LibraryManagement.Library.Dao;

import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.Data.Member;
import com.LibraryManagement.Library.UpdateRequest.BookUpdateRequest;
import com.LibraryManagement.Library.UpdateRequest.MemberUpdateRequest;

import java.util.Optional;

public interface MemberDao {

    void insertMember(Member member);

    void updateMember(Integer id, Member member);

    Optional<Member> getMember(Integer id);

    void deleteMember(Integer id);
}
