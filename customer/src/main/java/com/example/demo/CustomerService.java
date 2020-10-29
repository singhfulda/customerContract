package com.example.demo;

import com.example.demo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerDetails(Long id) {
        Optional<Customer> customer= customerRepository.findById(id);
        if(customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return customer.get();
    }
}
