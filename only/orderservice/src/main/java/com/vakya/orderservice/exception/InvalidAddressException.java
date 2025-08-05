package com.vakya.orderservice.exception;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(String message){
        super(message);
    }
}
