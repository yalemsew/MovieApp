package com.org.moviemail.exception.internal;

public class SubscriptionNotFoundException extends RuntimeException{

    public SubscriptionNotFoundException(String message){
        super(message);
    }
}
