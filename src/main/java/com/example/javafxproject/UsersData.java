package com.example.javafxproject;


public class UsersData extends LibraryEntity {

    private String password;

    public UsersData(int id, String name, String password) {

        super(id, name);
        this.password = password;
    }

    // Association
    public void borrowBook(BooksData book) {
        System.out.println(this.getName() + " has borrowed: " + book.getName());
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", name='" + getName() + '\'' + ", Password='" + password + '\'' + '}';
    }
}