package com.example.demo.web.rest;

import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{id}")
    private ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerDetails(id));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void customerNotFoundHandler(CustomerNotFoundException ex) {
    }
}
