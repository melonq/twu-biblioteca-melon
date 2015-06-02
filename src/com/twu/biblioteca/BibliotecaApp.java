package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public static String getWelcomeMessage() {
        return "Welcome to Biblioteca!\n";
    }

    public static String getMenuMessage() {
        return "Please enter a number to select the option:\n" +
                "1. Show List Books.\n";
    }

    public static String getInvalidErrorMessage() {
        return "Select a valid option!\n";
    }

    public static void main(String[] args) {
        initBookList();

        System.out.print(getWelcomeMessage());
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
        System.out.print(getMenuMessage());
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
        System.out.print(getInvalidErrorMessage());
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
