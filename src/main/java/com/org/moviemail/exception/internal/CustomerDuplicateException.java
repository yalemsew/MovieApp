package com.org.moviemail.exception.internal;

public class CustomerDuplicateException extends RuntimeException{
    public CustomerDuplicateException(String message){
        super(message);
    }
}
