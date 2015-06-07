package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    public static List<Book> bookList = new ArrayList<Book>();
    public static List<Movie> movieList = new ArrayList<Movie>();

    public String getWelcomeMessage() {
        return "Welcome to Biblioteca!\n";
    }

    public String getMenuMessage() {
        return "Please enter a number to select the option:\n" +
                "1. Show List Books.\n" +
                "2. Checkout Books.\n" +
                "3. Return Books.\n" +
                "4. Show List Movies." +
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

    public String getCheckoutFailedMessage() {
        return "That book is not available.\n";
    }

    public String getReturnSuccessfulMessage() {
        return "Thank you for returning the book.\n";
    }

    public String getReturnFailedMessage() {
        return "That is not a valid book to return.\n";
    }

    public static void main(String[] args) {
        new BibliotecaApp().startWith(new ArrayList<Book>());
    }

    public void startWith(List<Book> bookList) {
        if (bookList.isEmpty()) {
            initBookList();
        } else {
            this.bookList = bookList;
        }
        initMovieList();
        System.out.print(getWelcomeMessage());
        printMenuOptions();

        startMonitor();
    }

    public void handleInput(String input, Scanner scanner) {
        if (input.equals("1")) {
            printBookList();
        } else if (input.equals("2")) {
            startCheckoutBook(scanner);
        } else if (input.equals("3")) {
            startReturnBook(scanner);
        } else if (input.equals("4")) {
            printMovieList();
        } else {
            printInvalidErrorMessage();
        }
    }

    private void startCheckoutBook(Scanner scanner) {
        System.out.println("Please input the check-out book name:");
        String bookName = scanner.nextLine();
        if (checkoutSuccessful(bookName)) {
            System.out.print(getCheckoutSuccessfulMessage());
        } else {
            System.out.print(getCheckoutFailedMessage());
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

    private void startReturnBook(Scanner scanner) {
        System.out.print("Please input the book name which you want to return:\n");
        String bookName = scanner.nextLine();
        if (returnSuccessful(bookName)) {
            System.out.print(getReturnSuccessfulMessage());
        } else {
            System.out.print(getReturnFailedMessage());
        }
    }

    private boolean returnSuccessful(String bookName) {
        for(Book book : bookList) {
            if (book.getName().equals(bookName) && book.isCheckedOut()) {
                book.setCheckedOut(false);
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

    private static void printMovieList() {
        System.out.println("List Movies:");
        for(Movie movie : movieList) {
            if (!movie.isCheckedOut()) System.out.println(movie);
        }
    }

    private static void initMovieList() {
        movieList = new ArrayList<Movie>();
        movieList.add(new Movie("Spider-Man", "2002", "Sam Raimi", "6.7/10"));
    }
}
