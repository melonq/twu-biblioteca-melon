package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private BibliotecaApp bibliotecaApp;
    private ByteArrayOutputStream outContent;
    private StringBuilder expectedContent;
    private List<Book> bookList;

    @Before
    public void setUp() {
        bibliotecaApp = new BibliotecaApp();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        bookList = new ArrayList<Book>();

        expectedContent = new StringBuilder();
        expectedContent.append(bibliotecaApp.getWelcomeMessage());
        expectedContent.append(bibliotecaApp.getMenuMessage());
    }

    @Test
    public void mainPageShouldHaveWelcomeMessage() {
        startBibliotecaAppWithInput("0");

        assertTrue(outContent.toString().contains("Welcome to Biblioteca!"));
    }

    @Test
    public void mainPageShouldHaveMenuOptions() {
        startBibliotecaAppWithInput("0");

        assertTrue(outContent.toString().contains("Please enter a number to select the option:"));
        assertTrue(outContent.toString().contains("1. Show List Books."));
        assertTrue(outContent.toString().contains("2. Checkout Books."));
        assertTrue(outContent.toString().contains("3. Return Books."));
        assertTrue(outContent.toString().contains("0. Quit."));
    }

    @Test
    public void shouldQuitProgramWhenSelectQuit() {
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("0");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldNotQuitUntilSelectQuit() {
        expectedContent.append(getBookList());
        expectedContent.append(getBookList());
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("1\n1\n0");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldNotSeeTheBookAfterCheckoutIt() {
        expectedContent.append("Please input the check-out book name:\n");
        expectedContent.append(bibliotecaApp.getCheckoutSuccessfulMessage());
        expectedContent.append("List Books:\nEffective C++\t#1991\t#Scott Meyers\n");
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("2\nHead First Java\n1");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldSeeCheckoutFailedMessageWhenCheckoutBookFailed() {
        expectedContent.append("Please input the check-out book name:\n");
        expectedContent.append(bibliotecaApp.getCheckoutFailedMessage());
        expectedContent.append(bibliotecaApp.getQuitMessage());

        bookList.add(new Book("Head First Java", "1995", "KathySierra"));
        bookList.get(0).setCheckedOut(true);
        bookList.add(new Book("Effective C++", "1991", "Scott Meyers"));

        startBibliotecaAppWithInput("2\nHead First Java\n");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldSeeTheCheckedOutBookAfterItReturned() {
        bookList.add(new Book("Head First Java", "1995", "KathySierra"));
        bookList.add(new Book("Effective C++", "1991", "Scott Meyers"));
        for(Book book : bookList) {
            book.setCheckedOut(true);
        }

        expectedContent.append("Please input the book name which you want to return:\n");
        expectedContent.append(bibliotecaApp.getReturnSuccessfulMessage());
        expectedContent.append("List Books:\nEffective C++\t#1991\t#Scott Meyers\n");
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("3\nEffective C++\n1");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldSeeReturnFailedMessageWhenReturnBookFailed() {
        expectedContent.append("Please input the book name which you want to return:\n");
        expectedContent.append(bibliotecaApp.getReturnFailedMessage());
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("3\nEffective C++\n");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void mainPageShouldNotHaveListBooks() {
        startBibliotecaAppWithInput("0");
        String bookInfo = getBookList();

        assertFalse(outContent.toString().contains(bookInfo));
    }

    @Test
    public void shouldShowListBooksWhenSelectListBooksOption() {
        startBibliotecaAppWithInput("1");
        String bookInfo = getBookList();

        assertTrue(outContent.toString().contains(bookInfo));
    }

    @Test
    public void shouldShowInvalidErrorMessageWhenSelectInvalidOptions() {
        startBibliotecaAppWithInput("invalid input");
        String invalidErrorMessage = "Select a valid option!";

        assertTrue(outContent.toString().contains(invalidErrorMessage));
    }

    private void startBibliotecaAppWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        bibliotecaApp.startWith(bookList);
    }

    private String getBookList() {
        return "List Books:\n" +
                "Head First Java\t#1995\t#KathySierra\n" +
                "Effective C++\t#1991\t#Scott Meyers\n";
    }
}