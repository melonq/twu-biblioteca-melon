package com.twu.biblioteca;

/**
 * Created by xzhang on 6/7/15.
 */
public class Movie {
    private String name, year, director, rating;
    private boolean checkedOut = false;

    Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + "\t" + year + "\t" + director + "\t" + rating;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getName() {
        return name;
    }
}
