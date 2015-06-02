package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private StringBuilder expectedContent;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        expectedContent = new StringBuilder();
        expectedContent.append(BibliotecaApp.getWelcomeMessage());
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

    @Test
    public void shouldSeeExpectedMessageWhenInputIs0() {
        expectedContent.append(BibliotecaApp.getMenuMessage());
        expectedContent.append(BibliotecaApp.getInvalidErrorMessage());

        startBibliotecaAppWithInput("0");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    @Test
    public void shouldSeeExpectedMessageWhenInputIs1() {
        expectedContent.append(BibliotecaApp.getMenuMessage());
        expectedContent.append(getBookList());

        startBibliotecaAppWithInput("1");

        assertEquals(expectedContent.toString(), outContent.toString());
    }

    private void startBibliotecaAppWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        BibliotecaApp.main(new String[]{});
    }

    private String getBookList() {
        return "List Books:\n" +
                "Head First Java\t#1995\t#KathySierra\n" +
                "Effective C++\t#1991\t#Scott Meyers\n";
    }
}