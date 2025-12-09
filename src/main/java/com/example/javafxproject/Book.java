package com.example.javafxproject;

public class Book {

    private String bookid;
    private String title;
    private String author;
    private String publicationyear;
    private String genre;
    private String pages;
    private String language;
    private double price;

    public Book(String bookid, String title, String author, String publicationyear,
                String genre, String pages, String language, double price) {

        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.publicationyear = publicationyear;
        this.genre = genre;
        this.pages = pages;
        this.language = language;
        this.price = price;
    }

    public String getBookid() { return bookid; }
    public void setBookid(String bookid) { this.bookid = bookid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublicationyear() { return publicationyear; }
    public void setPublicationyear(String publicationyear) { this.publicationyear = publicationyear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPages() { return pages; }
    public void setPages(String pages) { this.pages = pages; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
