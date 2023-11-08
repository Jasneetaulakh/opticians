package com.example.demo.exceptions;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(Long id) {
        super("The booking with reference id " + id + " does not exist in our records.");
    }
}
