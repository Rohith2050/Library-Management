package com.LibraryManagement.Library.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Borrow {

    @Id
    private int id;
    private int member_id;
    private int book_id;
    private String borrowed_date;
    private String due_date;

    public int getId() {
        return id;
    }

    public int getMember_id() {
        return member_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getBorrowed_date() {
        return borrowed_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public Borrow(int id, int member_id, int book_id, String borrowed_date, String due_date) {
        this.id = id;
        this.member_id = member_id;
        this.book_id = book_id;
        this.borrowed_date = borrowed_date;
        this.due_date = due_date;
    }
}
