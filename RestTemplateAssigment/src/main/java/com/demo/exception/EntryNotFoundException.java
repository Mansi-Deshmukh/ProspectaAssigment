package com.demo.exception;

public class EntryNotFoundException extends Exception{
    
    public EntryNotFoundException(){

    }
    public EntryNotFoundException(String message){
        super(message);
    }
}
