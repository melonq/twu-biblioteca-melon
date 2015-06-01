package com.twu.biblioteca;

/**
 * Created by xzhang on 6/1/15.
 */
public class Book {
    private String bookName;

    public Book(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return bookName;
    }
}
