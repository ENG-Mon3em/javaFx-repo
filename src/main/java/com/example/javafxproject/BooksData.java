package com.example.javafxproject;

public class BooksData {

    public int id;
    public String bookName;
    public String authorName;
    public double Storage;
    public int PubYear;

    public BooksData() {
    }

    public BooksData(int id, String bookName, String authorName, double storage, int pubYear) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        Storage = storage;
        PubYear = pubYear;
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

    public double getStorage() {
        return Storage;
    }

    public void setStorage(double storage) {
        Storage = storage;
    }

    public int getPubYear() {
        return PubYear;
    }

    public void setPubYear(int pubYear) {
        PubYear = pubYear;
    }

    @Override
    public String toString() {
        return "BooksData{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", Storage=" + Storage +
                ", PubYear=" + PubYear +
                '}';
    }
}
