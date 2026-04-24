package com.example.javafxproject;

public class BooksData extends LibraryEntity {


    private String authorName;
    private double storage;
    private int pubYear;

    public BooksData(int id, String bookName) {
        super(id, bookName);
    }

    // Overloading
    public BooksData(int id, String bookName, String authorName, double storage, int pubYear) {

        super(id, bookName);
        this.authorName = authorName;
        this.storage = storage;
        this.pubYear = pubYear;
    }


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public String toString() {
        return "Book Details:\n" + "-------------------------------------\n" + "ID: " + getId() + "\n" + "Title: " + getName() + "\n" + "Author: " + authorName + "\n" + "Publication Year: " + pubYear + "\n" + "Storage: " + storage + " MB\n";
    }
}