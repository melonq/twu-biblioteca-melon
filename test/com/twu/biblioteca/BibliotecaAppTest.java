package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void mainPageShouldHaveWelcomeMessage() {
        BibliotecaApp.main(new String[]{});

        assertTrue( outContent.toString().contains("Welcome to Biblioteca!") );
    }

    @Test
    public void mainPageShouldHaveListBooks() {
        BibliotecaApp.main(new String[]{});
        String bookInfo = "Head First Java\t#1995\t#KathySierra";

        assertTrue( outContent.toString().contains(bookInfo) );
    }
}