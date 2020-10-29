package com.example.demo.service;


import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDTO;
import com.example.demo.service.mapper.CustomerMapper;
import com.example.demo.web.rest.CustomerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerService(customerRepository, customerMapper);

    }

    @Test
    public void getCustomerDetails_returnsCustomerInfo() {
        Customer testCustomer = new Customer(100L, "testCustomer");
        given(customerRepository.findById(any())).willReturn(Optional.of(testCustomer));
        given(customerMapper.toDto(any())).willReturn(new CustomerDTO(testCustomer.getId(), testCustomer.getName()));

        CustomerDTO customer = customerService.getCustomerDetails(100L);

        assertThat(customer.getId()).isEqualTo(100L);
        assertThat(customer.getName()).isEqualTo("testCustomer");
    }

    @Test(expected = CustomerNotFoundException.class)
    public void getCustomerDetails_WhenCustomerNotFound() throws Exception {
        given(customerRepository.findById(100L)).willReturn(Optional.ofNullable(null));

        customerService.getCustomerDetails(100L);
    }

}
