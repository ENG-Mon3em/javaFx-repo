package com.example.javafxproject;

public class UsersData {

    public int id;
    public String name;
    public String Password;

    public UsersData(int id, String name, String Password) {
        this.id = id;
        this.name = name;
        this.Password = Password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", Password='" + Password + '\'' + '}';
    }
}
