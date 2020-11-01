package com.example.demo.service;

import com.example.demo.model.PoliceDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PoliceApiControllerTest {

    String baseUrl;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    private Long id;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + randomServerPort + "/v2/police";
    }

    @Test
    @Order(1)
    public void createPolice() {
        createPoliceWithId();

    }

    @Test
    @Order(2)
    public void getRequest() {
        createPoliceWithId();
        ResponseEntity<PoliceDTO> dtoResponseEntity = restTemplate.getForEntity(baseUrl + "/" + id, PoliceDTO.class);
        Assert.assertNotNull(dtoResponseEntity.getBody());
        Assert.assertEquals(id, dtoResponseEntity.getBody().getId());
    }

    @Test
    @Order(3)
    public void updatePolice() throws Exception {
        createPoliceWithId();

        ResponseEntity<PoliceDTO> dtoResponseEntity = restTemplate.getForEntity(baseUrl + "/" + id, PoliceDTO.class);
        Assert.assertNotNull(dtoResponseEntity.getBody());
        Assert.assertEquals(id, dtoResponseEntity.getBody().getId());

        PoliceDTO policeDTO = dtoResponseEntity.getBody();
        policeDTO.setName("updatedTestPolicy");
        policeDTO.setFaceValue(BigDecimal.ONE);

        ResponseEntity<PoliceDTO> resultUpdated = restTemplate.exchange(new URI(baseUrl + "/" + id), HttpMethod.PUT, new HttpEntity<PoliceDTO>(policeDTO), PoliceDTO.class);
        Assert.assertEquals(policeDTO.getId(), resultUpdated.getBody().getId());
        Assert.assertEquals(policeDTO.getName(), resultUpdated.getBody().getName());
        Assert.assertEquals(policeDTO.getFaceValue(), resultUpdated.getBody().getFaceValue());
    }

    @Test
    @Order(4)
    public void deletePolice() throws Exception {
        createPoliceWithId();
        restTemplate.delete("/v2/police/" + id);

        ResponseEntity<PoliceDTO> dtoResponseEntity = restTemplate.getForEntity(baseUrl + "/" + id, PoliceDTO.class);
        Assert.assertEquals(404, dtoResponseEntity.getStatusCodeValue());
    }

    private void createPoliceWithId() {
        PoliceDTO police = new PoliceDTO();
        police.setName("testPolicy");
        police.setFaceValue(BigDecimal.TEN);
        police.setCustomerId(1L);

        ResponseEntity<PoliceDTO> result = restTemplate.postForEntity(baseUrl, new HttpEntity<>(police), PoliceDTO.class);
        Assert.assertNotNull(result.getBody().getId());
        id = result.getBody().getId();
        Assert.assertEquals(201, result.getStatusCodeValue());
    }

}