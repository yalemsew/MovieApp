package com.org.moviemail.exception.internal;

public class DVDNotRentedException extends RuntimeException{
    public DVDNotRentedException(String message){
        super(message);
    }
}
