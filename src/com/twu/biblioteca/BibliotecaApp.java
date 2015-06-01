package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public static void main(String[] args) {
        initBookList();

        System.out.println("Welcome to Biblioteca!");

        printBookList();
    }

    private static void printBookList() {
        System.out.println("List Books:");
        for(Book book : bookList) {
            System.out.println(book);
        }
    }

    private static void initBookList() {
        bookList.add(new Book("Head First Java", "1995", "KathySierra"));
        bookList.add(new Book("Effective C++", "1991", "Scott Meyers"));
    }
}
