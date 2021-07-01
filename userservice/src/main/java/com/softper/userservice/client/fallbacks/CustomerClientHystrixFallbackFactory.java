package com.softper.userservice.client.fallbacks;

import com.softper.userservice.client.CustomerClient;

import com.tropsmart.resources.comunications.CustomerBoundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class CustomerClientHystrixFallbackFactory implements  CustomerClient {

    @Override
    public ResponseEntity<CustomerBoundResponse> generateNewCustomer(int personId) {
        CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("generateNewCostumer", "fallback response",-1);
        return ResponseEntity.ok(customerBoundResponse);
    }

    @Override
    public ResponseEntity<CustomerBoundResponse> findCustomerByPersonId(int personId) {
        CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("findCustomerByPersonId", "fallback response",-1);
        return ResponseEntity.ok(customerBoundResponse);
    }

    @Override
    public ResponseEntity<CustomerBoundResponse> findCustomerById(int customerId) {
        CustomerBoundResponse customerBoundResponse = new CustomerBoundResponse("findCustomerById", "fallback response",-1);
        return ResponseEntity.ok(customerBoundResponse);
    }
}
