package com.LibraryManagement.Library.UpdateRequest;

public record BookUpdateRequest(String title,String author,String isbn,String publishedDate,int availableCopies) {
}
