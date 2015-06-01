package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public static void main(String[] args) {
        initBookList();

        System.out.println("Welcome to Biblioteca!");

        System.out.println(bookList.toString());
    }

    private static void initBookList() {
        bookList.add(new Book("Head First Java"));
    }
}
