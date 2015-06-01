package com.twu.biblioteca;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BibliotecaAppTest extends TestCase {

    @Test
    public void testMain() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.main(new String[]{});
        assertEquals("Welcome to Biblioteca!\n", outContent.toString());
    }
}