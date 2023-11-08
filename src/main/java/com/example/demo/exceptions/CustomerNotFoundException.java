package com.example.demo.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super("The customer with id " + id + " does not exist in our system");
    }
}
