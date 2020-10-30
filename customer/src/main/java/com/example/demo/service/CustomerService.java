package com.example.demo.service;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDTO;
import com.example.demo.service.mapper.CustomerMapper;
import com.example.demo.web.rest.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO getCustomerDetails(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return customerMapper.toDto(customer.get());
    }

    public CustomerDTO saveCustomerDetails(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null) {
            Customer customer = customerMapper.toEntity(customerDTO);
            customer = customerRepository.save(customer);
            return customerMapper.toDto(customer);
        } else {
            throw new CustomerAlreadyExistsException();
        }
    }

    public CustomerDTO updateCustomerDetails(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null) {
            throw new CustomerDontExistsException();
        } else {
            if (customerRepository.existsById(customerDTO.getId())) {
                Customer customer = customerMapper.toEntity(customerDTO);
                customer = customerRepository.save(customer);
                return customerMapper.toDto(customer);
            } else {
                throw new CustomerDontExistsException();
            }
        }
    }
}
