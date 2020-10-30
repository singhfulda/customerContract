package com.example.demo.service;


import com.example.demo.domain.Customer;
import com.example.demo.domain.Police;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDTO;
import com.example.demo.service.dto.PoliceDTO;
import com.example.demo.service.mapper.CustomerMapper;
import com.example.demo.web.rest.CustomerNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

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
        List<Police> polices = new ArrayList<>();
        polices.add(new Police(100L, "police1", BigDecimal.TEN));
        testCustomer.setPolices(polices);
        given(customerRepository.findById(any())).willReturn(Optional.of(testCustomer));
        given(customerMapper.toDto(any())).willReturn(new CustomerDTO(testCustomer.getId(), testCustomer.getName(), Collections.singletonList(new PoliceDTO(100L, "police1", BigDecimal.TEN))));

        CustomerDTO customer = customerService.getCustomerDetails(100L);

        assertThat(customer.getId()).isEqualTo(100L);
        assertThat(customer.getName()).isEqualTo("testCustomer");
        assertThat(customer.getPolices().size()).isEqualTo(1);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void getCustomerDetails_WhenCustomerNotFound() throws Exception {
        lenient().when(customerRepository.findById(100L)).thenReturn(Optional.ofNullable(null));

        customerService.getCustomerDetails(100L);
    }

    @Test
    public void saveCustomerDetails_shouldSaveCustomerAndReturn() {
        Customer customer = new Customer(100L, "testCustomer");


        CustomerDTO customerSavedDTO = new CustomerDTO(100L, "testCustomer");
        given(customerMapper.toDto(any())).willReturn(customerSavedDTO);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("testCustomer");

        CustomerDTO customerSaved = customerService.saveCustomerDetails(customerDTO);

        Assertions.assertThat(customerSaved.getId()).isEqualTo(customer.getId());
        Assertions.assertThat(customerSaved.getName()).isEqualTo(customer.getName());
    }

    @Test
    public void saveCustomerDetails_shouldSaveCustomerAndNotPolicies() {

        CustomerDTO customerSavedDTO = new CustomerDTO(100L, "testCustomer");
        given(customerMapper.toDto(any())).willReturn(customerSavedDTO);

        PoliceDTO police = new PoliceDTO();
        police.setName("police1");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("testCustomer");
        customerDTO.setPolices(Collections.singletonList(police));

        CustomerDTO customerSaved = customerService.saveCustomerDetails(customerDTO);

        Assertions.assertThat(customerSaved.getId()).isEqualTo(customerSavedDTO.getId());
        Assertions.assertThat(customerSaved.getName()).isEqualTo(customerSavedDTO.getName());
        assertThat(customerSaved.getPolices()).isNullOrEmpty();
    }

    @Test(expected = CustomerAlreadyExistsException.class)
    public void saveCustomerDetailsWithIdPresent_shouldThrowException() {
        Customer customer = new Customer(100L, "testCustomer");
        CustomerDTO customerDTO = new CustomerDTO(100L, "testCustomer");

        customerService.saveCustomerDetails(customerDTO);
    }

    @Test(expected = CustomerDontExistsException.class)
    public void updateCustomerDetailsWithoutId_shouldThrowException() {
        Customer customer = new Customer(100L, "testCustomer");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("testCustomer");

        CustomerDTO customerSaved = customerService.updateCustomerDetails(customerDTO);
    }

    @Test(expected = CustomerDontExistsException.class)
    public void updateCustomerDetailsWithIdNotSavedInDatabase_shouldThrowException() {
        Customer customer = new Customer(100L, "testCustomer");
        Mockito.lenient().when(customerRepository.findById(100L)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = new CustomerDTO(100L, "testCustomer");

        CustomerDTO customerSaved = customerService.updateCustomerDetails(customerDTO);
    }


}
