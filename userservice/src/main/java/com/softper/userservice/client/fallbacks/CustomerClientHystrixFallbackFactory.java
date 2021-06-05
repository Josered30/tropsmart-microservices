package com.softper.userservice.client.fallbacks;

import com.softper.userservice.client.CustomerClient;
import com.softper.userservice.models.Customer;
import com.softper.userservice.resources.comunications.CustomerBoundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class CustomerClientHystrixFallbackFactory implements  CustomerClient {
    @Override
    public ResponseEntity<CustomerBoundResponse> findCustomersById(int cargoId) {
        CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse();

    }

    @Override
    public ResponseEntity<Customer> generateNewCustomer(int personId) {
        return null;
    }
}
