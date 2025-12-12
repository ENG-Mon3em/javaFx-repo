package com.example.javafxproject;

public class BookPre {
    private String name;
    private String id;

    public BookPre(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookPre{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}