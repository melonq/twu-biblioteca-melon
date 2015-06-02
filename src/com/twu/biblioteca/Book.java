package com.twu.biblioteca;

/**
 * Created by xzhang on 6/1/15.
 */
public class Book {
    private String name, author, yearPublished;
    private boolean checkedOut = false;

    public Book(String name, String yearPublished, String author) {
        this.name = name;
        this.yearPublished = yearPublished;
        this.author = author;
    }

    @Override
    public String toString() {
        return name + "\t#" + yearPublished + "\t#" + author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getName() {
        return name;
    }
}
