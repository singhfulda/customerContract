package com.example.demo;

import com.example.demo.domain.Customer;


public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public Customer getCustomerDetails(Long id) {
        Customer customer= customerRepository.findById(id);
        if(customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
