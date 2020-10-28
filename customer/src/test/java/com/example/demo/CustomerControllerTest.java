package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCustomer_ShouldReturnCustomer()  throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("testCustomer"))
                .andExpect(jsonPath("id").value(100));
    }
}
