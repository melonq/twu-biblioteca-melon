package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void mainPageShouldHaveWelcomeMessage() {
        startBibliotecaAppWithInput("0");

        assertTrue(outContent.toString().contains("Welcome to Biblioteca!"));
    }

    @Test
    public void mainPageShouldNotHaveListBooks() {
        startBibliotecaAppWithInput("0");
        String bookInfo = "Head First Java\t#1995\t#KathySierra\nEffective C++\t#1991\t#Scott Meyers";

        assertFalse(outContent.toString().contains(bookInfo));
    }

    @Test
    public void shouldShowListBooksWhenSelectListBooksOption() {
        startBibliotecaAppWithInput("1");
        String bookInfo = "Head First Java\t#1995\t#KathySierra\nEffective C++\t#1991\t#Scott Meyers";

        assertTrue(outContent.toString().contains(bookInfo));
    }

    private void startBibliotecaAppWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        BibliotecaApp.main(new String[]{});
    }
}