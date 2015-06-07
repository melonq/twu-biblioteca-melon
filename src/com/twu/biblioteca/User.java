package com.twu.biblioteca;

/**
 * Created by xzhang on 6/7/15.
 */
public class User {
    private String libraryNumber, password, name, email, phone;

    User(String libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public boolean verified(String libraryNumber, String password) {
        if (this.libraryNumber.equals(libraryNumber) && this.password.equals(password))
            return true;
        else
            return false;
    }

    public String getUserInformation() {
        return "Name:\t" + name + "\n" +
                "Email Address:\t" + email + "\n" +
                "Phone Number:\t" + phone;

    }
}
