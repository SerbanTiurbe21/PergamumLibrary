package com.example.MySpringApi;

import java.util.Locale;
import java.util.UUID;

public class Book {
    private String title;
    private String author;
    // this should be an unique id
    // I added this in order to do a safe delete after the unique ID, because if I want to delete after the title there may be the situation where two or more books have the same title
    // and it may be necessary to do a lot more verifications in order to be sure that you delete the correct book
    private String id;

    public Book() {
    }

    public Book(String title, String author) {
        this.id = String.valueOf(Math.random()+1);
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                '}';
    }
}
