package com.example.demo.service;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return unwrapCustomer(customer, id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerName(Long id, String name) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer unwrappedCustomer = unwrapCustomer(customer, id);
        unwrappedCustomer.setName(name);
        return customerRepository.save(unwrappedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer unwrappedCustomer = unwrapCustomer(customer, id);
        customerRepository.delete(unwrappedCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>)customerRepository.findAll();
    }

    static Customer unwrapCustomer(Optional<Customer> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else throw new CustomerNotFoundException(id);
    }
}
