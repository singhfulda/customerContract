package com.example.demo.web.rest;

import com.example.demo.TestUtil;
import com.example.demo.service.CustomerAlreadyExistsException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
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
    public void getCustomer_ShouldReturnCustomer() throws Exception {
        given(customerService.getCustomerDetails(100L)).willReturn(new CustomerDTO(100L, "testCustomer"));

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

    @Test
    public void postCustomer_ShouldCreateAndReturnCustomer() throws Exception {
        CustomerDTO testCustomer = new CustomerDTO();
        testCustomer.setName("testCustomer");
        given(customerService.saveCustomerDetails(any())).willReturn(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("testCustomer"));


    }

    @Test
    public void postCustomerWithId_ShouldNotSaveCustomer() throws Exception {
        CustomerDTO testCustomer = new CustomerDTO(100L, "testCustomer");
        given(customerService.saveCustomerDetails(any())).willThrow(CustomerAlreadyExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testCustomer)))
                .andExpect(status().isBadRequest());


    }


    @Test
    public void putCustomer_ShouldUpdateAndReturnCustomer() throws Exception {
        CustomerDTO testCustomer = new CustomerDTO(100L, "testCustomer");
        given(customerService.updateCustomerDetails(any())).willReturn(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.put("/customer")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(testCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("testCustomer"))
                .andExpect(jsonPath("id").value(100));
    }

}
