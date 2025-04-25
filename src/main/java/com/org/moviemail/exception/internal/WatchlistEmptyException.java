package com.org.moviemail.exception.internal;

public class WatchlistEmptyException extends RuntimeException{
    public WatchlistEmptyException(String message){
        super(message);
    }
}
