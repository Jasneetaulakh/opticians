package com.example.demo.service;

import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomerName(Long id, String name);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
}
