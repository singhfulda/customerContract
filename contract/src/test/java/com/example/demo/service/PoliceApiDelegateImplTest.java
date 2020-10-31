package com.example.demo.service;

import com.example.demo.TestUtil;
import com.example.demo.model.Police;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PoliceApiDelegateImplTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Order(1)
    void createPolice() throws Exception {
        Police police = new Police();
        police.setName("testPolicy");
        police.setFaceValue(BigDecimal.TEN);
        police.setCustomerId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/v2/police")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(police)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("testPolicy"));
    }

    @Test
    @Order(2)
    void getRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/police/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name" ).value("testPolicy"));
    }

    @Test
    @Order(3)
    void getPoliceById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/police/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name" ).value("testPolicy"));
    }

    @Test
    @Order(4)
    void updatePolice() throws Exception {
        Police police = new Police();
        police.setName("testPolicy");
        police.setFaceValue(BigDecimal.TEN);
        police.setCustomerId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/v2/police")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(police)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("testPolicy"));

        police.setName("testPolicyUpdated");

        mockMvc.perform(MockMvcRequestBuilders.post("/v2/police")
                .contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(police)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("testPolicyUpdated"));
    }

    @Test
    @Order(5)
    void deletePolice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(new URI("/v2/police/1")))
                .andExpect(status().isGone());
    }

}