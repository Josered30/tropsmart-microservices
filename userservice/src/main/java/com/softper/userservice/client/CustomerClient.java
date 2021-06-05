package com.softper.userservice.client;

import org.springframework.web.bind.annotation.RequestMapping;

import com.softper.userservice.models.Customer;
import com.softper.userservice.resources.comunications.CustomerBoundResponse;
import com.softper.userservice.resources.outputs.CustomerOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("customer-service")
@RequestMapping("/customerservice/customers")
public interface CustomerClient {
    
    @GetMapping("/by-id/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customerId") int customerId);

    @PostMapping("/{personId}")
    public ResponseEntity<Customer> generateNewCustomer(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-personId/{personId}")
    public ResponseEntity<Customer> getCustomersByPersonId(@PathVariable(value = "personId") int personId);

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerBoundResponse> findCustomersById(@PathVariable(value = "customerId") int customerId);
}
