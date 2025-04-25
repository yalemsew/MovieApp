package com.org.moviemail.exception.internal;

public class WatchListLimitReachedException extends RuntimeException{
    public WatchListLimitReachedException(String message){
        super(message);
    }
}
