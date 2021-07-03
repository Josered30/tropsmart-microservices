package com.softper.customerservice;


import java.util.Optional;

import com.softper.customerservice.models.Benefit;
import com.softper.customerservice.models.Customer;
import com.softper.customerservice.repositories.ICustomerRepository;
import com.softper.customerservice.services.ICustomerService;
import com.softper.customerservice.servicesImp.CustomerService;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerserviceTests {
    
    @Mock
    private ICustomerRepository customerRepository;

    private ICustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCredits(100.0);
        
        Mockito.when(customerRepository.findById(1))
            .thenReturn(Optional.of(customer));
    }

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCredits(100.0);

        Assertions.assertThat(customer.getId()).isEqualTo(1);
    }

    @Test
    public void whenValidGetFirstNameLastName_ThenReturnBenefit() throws Exception {
        Benefit benefit = new Benefit();
        benefit.setId(1);
        benefit.setName("40% descuento");
        benefit.setCode("US2937479823123");

        Assertions.assertThat(benefit.getId()).isEqualTo(1);
    }
}
