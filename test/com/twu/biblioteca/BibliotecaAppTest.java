package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private BibliotecaApp bibliotecaApp;
    private ByteArrayOutputStream outContent;
    private StringBuilder expectedContent;

    @Before
    public void setUp() {
        bibliotecaApp = new BibliotecaApp();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expectedContent = new StringBuilder();
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
        assertTrue(outContent.toString().contains("0. Quit."));
    }

    @Test
    public void shouldQuitProgramWhenSelectQuit() {
        expectedContent.append(bibliotecaApp.getWelcomeMessage());
        expectedContent.append(bibliotecaApp.getMenuMessage());
        expectedContent.append(bibliotecaApp.getQuitMessage());

        startBibliotecaAppWithInput("0");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldNotQuitUntilSelectQuit() {
        expectedContent.append(bibliotecaApp.getWelcomeMessage());
        expectedContent.append(bibliotecaApp.getMenuMessage());
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

        System.setIn(new ByteArrayInputStream("Head First Java".getBytes()));
        bibliotecaApp.handleInput("2");

        bibliotecaApp.handleInput("1");

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
        bibliotecaApp.start();
    }

    private String getBookList() {
        return "List Books:\n" +
                "Head First Java\t#1995\t#KathySierra\n" +
                "Effective C++\t#1991\t#Scott Meyers\n";
    }
}