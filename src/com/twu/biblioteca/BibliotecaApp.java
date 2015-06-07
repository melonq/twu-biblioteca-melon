package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    public static List<Book> bookList = new ArrayList<Book>();
    public static List<Movie> movieList = new ArrayList<Movie>();
    public static List<User> userList = new ArrayList<User>();

    public String getWelcomeMessage() {
        return "Welcome to Biblioteca!\n";
    }

    public String getMenuMessage() {
        return "Please enter a number to select the option:\n" +
                "1. Show List Books.\n" +
                "2. Checkout Books.\n" +
                "3. Return Books.\n" +
                "4. Show List Movies.\n" +
                "5. Checkout Movies.\n" +
                "0. Quit.\n";
    }

    public String getInvalidErrorMessage() {
        return "Select a valid option!\n";
    }

    public String getQuitMessage() {
        return "Bye!";
    }

    public String getCheckoutBookSuccessfulMessage() {
        return "Thank you! Enjoy the book.\n";
    }

    public String getCheckoutMovieSuccessfulMessage() {
        return "Thank you! Enjoy the movie.\n";
    }

    public String getCheckoutBookFailedMessage() {
        return "That book is not available.\n";
    }

    public String getCheckoutMovieFailedMessage() {
        return "That movie is not available.\n";
    }

    public String getReturnSuccessfulMessage() {
        return "Thank you for returning the book.\n";
    }

    public String getReturnFailedMessage() {
        return "That is not a valid book to return.\n";
    }

    public String getAuthorizationFailedMessage() {
        return "login failed. Please check your library number and password.\n";
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
        initUserList();

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
        } else if (input.equals("5")) {
            startCheckoutMovie(scanner);
        } else {
            printInvalidErrorMessage();
        }
    }

    private void startCheckoutBook(Scanner scanner) {
        System.out.println("Please input the check-out book name:");
        String bookName = scanner.nextLine();
        boolean userAuthorizationResult = startUserAuthorization(scanner);
        if (userAuthorizationResult == true) {
            if (checkoutBookSuccessful(bookName)) {
                System.out.print(getCheckoutBookSuccessfulMessage());
            } else {
                System.out.print(getCheckoutBookFailedMessage());
            }
        }
    }

    private boolean checkoutBookSuccessful(String bookName) {
        for(Book book : bookList) {
            if (book.getName().equals(bookName) && !book.isCheckedOut()) {
                book.setCheckedOut(true);
                return true;
            }
        }
        return false;
    }

    private void startCheckoutMovie(Scanner scanner) {
        System.out.println("Please input the check-out movie name:");
        String movieName = scanner.nextLine();
        if (checkoutMovieSuccessful(movieName)) {
            System.out.print(getCheckoutMovieSuccessfulMessage());
        } else {
            System.out.print(getCheckoutMovieFailedMessage());
        }
    }

    private boolean checkoutMovieSuccessful(String movieName) {
        for(Movie movie : movieList) {
            if (movie.getName().equals(movieName) && !movie.isCheckedOut()) {
                movie.setCheckedOut(true);
                return true;
            }
        }
        return false;
    }

    private void startReturnBook(Scanner scanner) {
        System.out.print("Please input the book name which you want to return:\n");
        String bookName = scanner.nextLine();
        if (returnBookSuccessful(bookName)) {
            System.out.print(getReturnSuccessfulMessage());
        } else {
            System.out.print(getReturnFailedMessage());
        }
    }

    private boolean returnBookSuccessful(String bookName) {
        for(Book book : bookList) {
            if (book.getName().equals(bookName) && book.isCheckedOut()) {
                book.setCheckedOut(false);
                return true;
            }
        }
        return false;
    }

    private boolean startUserAuthorization(Scanner scanner) {
        System.out.println("Please input your library number:");
        String libraryNumber = scanner.nextLine();
        System.out.println("Please input your password:");
        String password = scanner.nextLine();

        for (User user: userList) {
            if (user.verified(libraryNumber, password)) {
                return true;
            }
        }

        System.out.print(getAuthorizationFailedMessage());
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

    private static void initUserList() {
        userList = new ArrayList<User>();
        userList.add(new User("000-0001", "password01"));
        userList.add(new User("000-0002", "password02"));
    }
}
