package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public static void main(String[] args) {
        initBookList();

        System.out.println("Welcome to Biblioteca!");
        printMenuOptions();

        startMonitor();
    }

    public static void handleInput(int input) {
        switch (input) {
            case 1:
                printBookList();
                break;
            default:
                printInvalidErrorMessage();
                break;
        }

    }

    private static void printMenuOptions() {
        System.out.println("Please enter a number to select the option:");
        System.out.println("1. Show List Books.");
    }

    private static void startMonitor() {
        Scanner scanner = new Scanner(System.in);
        try {
            int input = scanner.nextInt();
            handleInput(input);
        }
        catch(Exception e) {
            printInvalidErrorMessage();
        }
    }

    private static void printInvalidErrorMessage() {
        System.out.println("Select a valid option!");
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
