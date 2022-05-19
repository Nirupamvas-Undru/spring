/*
package com.springboot.resortmanagement;


import com.springboot.resortmanagement.entity.Customer;
import com.springboot.resortmanagement.repository.CustomerRepository;
import com.springboot.resortmanagement.repository.ResortRepository;
import com.springboot.resortmanagement.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private ResortRepository resortRepository;

    @Test
    void listCustomers(){
        when(customerRepository.findAll()).thenReturn(Stream
                .of(new Customer("abc","sdfs","abcsdfs@gmail.com")).collect(Collectors.toList()));
        assertEquals(1, customerService.findAll().size());
    }

    @Test
    void getCustomersById(){
        Customer customers = new Customer("alpha","beta","alphabeta@gmail.com");
        when(customerRepository.getById(13)).thenReturn(customers);

        Customer customer = customerService.findById(13);
        assertEquals("alpha",customer.getFirstName());
        assertEquals("beta",customer.getLastName());
        assertEquals("alphabeta@gmail.com",customer.getEmail());
    }

}
*/
