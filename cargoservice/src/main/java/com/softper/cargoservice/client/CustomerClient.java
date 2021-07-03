package com.softper.cargoservice.client;

import org.springframework.web.bind.annotation.RequestMapping;

import com.softper.cargoservice.models.Customer;
import com.softper.cargoservice.resources.comunications.CustomerBoundResponse;
import com.softper.cargoservice.resources.outputs.CustomerOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("customer-service")
@RequestMapping("/customerservice/customers")
public interface CustomerClient {
    /*
    @GetMapping("/by-id/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customerId") int customerId);

    @GetMapping("/by-personId/{personId}")
    public ResponseEntity<Customer> getCustomersByPersonId(@PathVariable(value = "personId") int personId);
    */
    
    @GetMapping("/by-customerid/{customerId}")
    public ResponseEntity<CustomerBoundResponse> getCustomerModelById(@PathVariable(value = "customerId") int customerId);
    
}
