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
    

    @PostMapping("/{personId}")
    public ResponseEntity<CustomerBoundResponse> generateNewCustomer(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-customerid/{customerId}")
    public ResponseEntity<CustomerBoundResponse> getCustomerModelById(@PathVariable(value = "customerId") int customerId);
    
}
