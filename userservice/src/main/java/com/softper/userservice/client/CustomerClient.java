package com.softper.userservice.client;

import com.tropsmart.resources.comunications.CustomerBoundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

@FeignClient("customer-service")
@RequestMapping("/customerservice/customers")
public interface CustomerClient {


    @PostMapping("/{personId}")
    public ResponseEntity<CustomerBoundResponse> generateNewCustomer(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-person-id/{personId}")
    public ResponseEntity<CustomerBoundResponse> findCustomerByPersonId(@PathVariable(value = "personId") int personId);

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerBoundResponse> findCustomerById(@PathVariable(value = "customerId") int customerId);
}
