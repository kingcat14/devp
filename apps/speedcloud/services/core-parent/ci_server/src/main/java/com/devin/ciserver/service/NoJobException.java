package com.devin.ciserver.service;

public class NoJobException extends Exception {
    public NoJobException(){
        super();
    }
    public NoJobException(String message){
        super(message);
    }
}
