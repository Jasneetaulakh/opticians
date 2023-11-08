package com.example.demo.exceptions;

public class ExaminationNotFoundException extends RuntimeException{

    public ExaminationNotFoundException(Long customerId, Long profStaffId) {
        super("The examination with customer id " + customerId + " and staff id " + profStaffId + " does not exist in our system");
    }

    public ExaminationNotFoundException(Long examinationId) {
        super("The Examination with id " + examinationId + " does not exist in our system");
    }
}
