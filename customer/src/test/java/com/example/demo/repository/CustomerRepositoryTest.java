package com.example.demo.repository;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Police;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PoliceRepository policeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getCustomer_returnsCustomerDetails() throws Exception {
        Customer testCustomer = new Customer();
        testCustomer.setName("testCustomer");
        Customer newCustomer = entityManager.persistFlushFind(testCustomer);

        Optional<Customer> customerOptional = customerRepository.findById(newCustomer.getId());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            Assertions.assertThat(customer.getId()).isEqualTo(newCustomer.getId());
            Assertions.assertThat(customer.getName()).isEqualTo(newCustomer.getName());
        } else {
            fail();
        }
    }

    @Test
    public void getCustomer_returnsCustomerDetailsOnlyAndSavesNoPolices() throws Exception {

        int sizeBefore = policeRepository.findAll().size();
        Customer testCustomer = new Customer();
        testCustomer.setName("testCustomer");
        Police police1 = new Police();
        police1.setName("police1");
        police1.setFaceValue(BigDecimal.TEN);
        testCustomer.setPolices(Collections.singletonList(police1));
        Customer newCustomer = entityManager.persistFlushFind(testCustomer);

        int sizeAfter = policeRepository.findAll().size();
        Assertions.assertThat(sizeAfter).isEqualTo(sizeBefore);
        Assertions.assertThat(newCustomer.getName()).isEqualTo(testCustomer.getName());

    }

}