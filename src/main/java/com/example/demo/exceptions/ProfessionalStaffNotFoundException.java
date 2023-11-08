package com.example.demo.exceptions;

public class ProfessionalStaffNotFoundException extends RuntimeException{
    public ProfessionalStaffNotFoundException(Long id) {
        super("The staff member with id " + id + " does not exist in our system");
    }
}
