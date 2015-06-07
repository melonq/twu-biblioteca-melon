package com.twu.biblioteca;

/**
 * Created by xzhang on 6/7/15.
 */
public class User {
    private String libraryNumber, password;

    User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public boolean verified(String libraryNumber, String password) {
        if (this.libraryNumber.equals(libraryNumber) && this.password.equals(password))
            return true;
        else
            return false;
    }
}
