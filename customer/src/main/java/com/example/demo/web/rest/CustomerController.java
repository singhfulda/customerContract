package com.example.demo.web.rest;

import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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

    @PostMapping("/customer")
    private ResponseEntity<CustomerDTO> postCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        CustomerDTO result = customerService.saveCustomerDetails(customerDTO);
        return ResponseEntity.created(new URI("/customer/" + customerDTO.getId()))
                .body(result);
    }

    @PutMapping("/customer")
    private ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        CustomerDTO result = customerService.saveCustomerDetails(customerDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void customerNotFoundHandler(CustomerNotFoundException ex) {
    }
}
