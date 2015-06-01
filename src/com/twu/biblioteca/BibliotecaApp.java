package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public static void main(String[] args) {
        initBookList();

        System.out.println("Welcome to Biblioteca!");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        handleInput(input);
    }

    public static void handleInput(int input) {
        switch (input) {
            case 1:
                printBookList();
                break;
            default:
                break;
        }

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
