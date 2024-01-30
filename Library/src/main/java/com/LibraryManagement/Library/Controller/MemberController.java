package com.LibraryManagement.Library.Controller;

import com.LibraryManagement.Library.Data.Book;
import com.LibraryManagement.Library.Data.Member;
import com.LibraryManagement.Library.Service.BookService;
import com.LibraryManagement.Library.Service.MemberService;
import com.LibraryManagement.Library.UpdateRequest.MemberUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //CRUD - Operations for Members

    @PostMapping("add")
    public ResponseEntity<String> createMember(@RequestBody Member member){
       return memberService.addMember(member);
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateMember(@PathVariable("id")Integer id,@RequestBody Member member){
       return memberService.updateMember(id,member);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<Optional<Member>> readMember(@PathVariable("id")Integer id){
        return memberService.getMember(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id")Integer id){
       return memberService.deleteMember(id);
    }
}
