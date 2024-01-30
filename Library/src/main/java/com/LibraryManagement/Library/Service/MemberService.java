package com.LibraryManagement.Library.Service;

import com.LibraryManagement.Library.Dao.MemberDao;
import com.LibraryManagement.Library.Data.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    
    private final MemberDao memberDao;

    private  Member member;

    public MemberService(@Qualifier("jdbc1") MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public ResponseEntity<String> addMember(Member member) {
        memberDao.insertMember(member);
        return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateMember(Integer id, Member member) {
        memberDao.updateMember(id,member);
        return new ResponseEntity<>("Updated", HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Member>> getMember(Integer id) {
        return new ResponseEntity<>(memberDao.getMember(id), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteMember(Integer id) {
        memberDao.deleteMember(id);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.CREATED);
    }
}
