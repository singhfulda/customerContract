package com.example.demo;

import com.example.demo.domain.Customer;
import com.example.demo.messaging.PolicenListener;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PoliceRepository;
import com.example.demo.service.dto.PoliceSyncDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class KafkaTest {

    @SpyBean
    private PolicenListener policenListener;

    @Autowired
    private Sink channels;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PoliceRepository policeRepository;

    @Test
    public void testWhenPoliceMessageComesInThen() {

        Long before = policeRepository.count();


        PoliceSyncDTO policeSyncDTO = new PoliceSyncDTO();
        policeSyncDTO.setId(10L);
        policeSyncDTO.setFaceValue(BigDecimal.TEN);
        policeSyncDTO.setName("policy1");

        Customer customer = new Customer();
        customer.setName("testCustomer");
        customer = customerRepository.save(customer);

        policeSyncDTO.setCustomerId(customer.getId());

        this.channels.input().send(new GenericMessage<>(policeSyncDTO));

        Long afterSendCompletion = policeRepository.count();

        assertThat(afterSendCompletion).isEqualTo(before + 1);
        verify(this.policenListener, times(1)).listenPolicen(any());

    }
}
