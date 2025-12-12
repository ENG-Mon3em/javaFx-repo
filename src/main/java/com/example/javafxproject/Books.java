package com.example.javafxproject;

public class Books {

    public int id;
    public String bookName;
    public String authorName;
    public String bookDetails;

    public Books(int id, String bookName, String authorName, String bookDetails) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDetails = bookDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(String bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookDetails='" + bookDetails + '\'' +
                '}';
    }
}
