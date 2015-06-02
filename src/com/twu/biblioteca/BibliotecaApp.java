package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static List<Book> bookList = new ArrayList<Book>();

    public String getWelcomeMessage() {
        return "Welcome to Biblioteca!\n";
    }

    public String getMenuMessage() {
        return "Please enter a number to select the option:\n" +
                "1. Show List Books.\n" +
                "2. Checkout Books.\n" +
                "0. Quit.\n";
    }

    public String getInvalidErrorMessage() {
        return "Select a valid option!\n";
    }

    public String getQuitMessage() {
        return "Bye!";
    }

    public String getCheckoutSuccessfulMessage() {
        return "Thank you! Enjoy the book.\n";
    }

    public static void main(String[] args) {
        new BibliotecaApp().start();
    }

    public void start() {
        initBookList();

        System.out.print(getWelcomeMessage());
        printMenuOptions();

        startMonitor();
    }

    public void handleInput(String input, Scanner scanner) {
        if (input.equals("1")) {
            printBookList();
        } else if (input.equals("2")) {
            startCheckoutBook(scanner);
        } else {
            printInvalidErrorMessage();
        }
    }

    private void startCheckoutBook(Scanner scanner) {
        System.out.println("Please input the check-out book name:");
        String bookName = scanner.nextLine();
        if (checkoutSuccessful(bookName)) {
            System.out.print(getCheckoutSuccessfulMessage());
        }
    }

    private boolean checkoutSuccessful(String bookName) {
        for(Book book : bookList) {
            if (book.getName().equals(bookName) && !book.isCheckedOut()) {
                book.setCheckedOut(true);
                return true;
            }
        }
        return false;
    }

    private void printMenuOptions() {
        System.out.print(getMenuMessage());
    }

    private void startMonitor() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("0")) {
            handleInput(input, scanner);
            try {
                input = scanner.nextLine();
            }
            catch (Exception e) {
                break;
            }
        }
        System.out.print(getQuitMessage());
    }

    private void printInvalidErrorMessage() {
        System.out.print(getInvalidErrorMessage());
    }

    private static void printBookList() {
        System.out.println("List Books:");
        for(Book book : bookList) {
            if (!book.isCheckedOut()) System.out.println(book);
        }
    }

    private static void initBookList() {
        bookList = new ArrayList<Book>();
        bookList.add(new Book("Head First Java", "1995", "KathySierra"));
        bookList.add(new Book("Effective C++", "1991", "Scott Meyers"));
    }
}
