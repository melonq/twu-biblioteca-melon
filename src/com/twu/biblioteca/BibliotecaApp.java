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
                "1. Show List Books.\n" +
                "0. Quit.\n";
    }

    public static String getInvalidErrorMessage() {
        return "Select a valid option!\n";
    }

    public static String getQuitMessage() {
        return "Bye!";
    }

    public static void main(String[] args) {
        initBookList();

        System.out.print(getWelcomeMessage());
        printMenuOptions();

        startMonitor();
    }

    public static void handleInput(String input) {
        if (input.equals("1")) {
            printBookList();
        } else {
            printInvalidErrorMessage();
        }
    }

    private static void printMenuOptions() {
        System.out.print(getMenuMessage());
    }

    private static void startMonitor() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (!input.equals("0")) {
            handleInput(input);
            try {
                input = scanner.next();
            }
            catch (Exception e) {
                break;
            }
        }
        System.out.print(getQuitMessage());
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
