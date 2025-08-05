package com.vakya.orderservice.exception;

public class InvalidProductException extends Exception{
    public InvalidProductException(String message){
        super(message);
    }
}
