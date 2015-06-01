package com.twu.biblioteca;

/**
 * Created by xzhang on 6/1/15.
 */
public class Book {
    private String bookName, author, yearPublished;

    public Book(String bookName, String yearPublished, String author) {
        this.bookName = bookName;
        this.yearPublished = yearPublished;
        this.author = author;
    }

    @Override
    public String toString() {
        return bookName + "\t#" + yearPublished + "\t#" + author;
    }
}
