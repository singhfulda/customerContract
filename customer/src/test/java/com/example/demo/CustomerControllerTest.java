package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomer_ShouldReturnCustomer()  throws Exception {
        given(customerService.getCustomerDetails(100L)).willReturn(new Customer(100L, "testCustomer"));

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("testCustomer"))
                .andExpect(jsonPath("id").value(100));
    }

    @Test
    public void getCustomer_notFound() throws Exception {
        given(customerService.getCustomerDetails(100L)).willThrow(new CustomerNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/100"))
                .andExpect(status().isNotFound());
    }

}
