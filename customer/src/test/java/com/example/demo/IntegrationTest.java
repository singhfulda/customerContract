package com.example.demo;

import com.example.demo.domain.Customer;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCustomerReturnsCustomerDetails() throws Exception {
        //arrange

        //act
        ResponseEntity<Customer> response = restTemplate.getForEntity("/customer/100", Customer.class);

        //assert Expected :200 OK
        //Actual   :404 NOT_FOUND
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getName()).isEqualTo("testCustomer");
        Assertions.assertThat(response.getBody().getId()).isEqualTo(100L);

    }
}
